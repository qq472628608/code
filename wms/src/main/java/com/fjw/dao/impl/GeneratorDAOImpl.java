package com.fjw.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.IGeneratorDAO;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;

import lombok.Setter;

public class GeneratorDAOImpl<T> implements IGeneratorDAO<T> {
	@Setter@Autowired
	protected SessionFactory sessionFactory;
	private Class<T> clz;
 	protected GeneratorDAOImpl() {
		ParameterizedType type = (ParameterizedType) this.getClass().getGenericSuperclass();
		clz = (Class<T>) type.getActualTypeArguments()[0];
	}
	
	public void save(T obj) {
		Session session = sessionFactory.getCurrentSession();
		session.save(obj);
	}

	public void delete(Long id) {
		Session session = sessionFactory.getCurrentSession();
		Object obj = session.get(clz, id);
		session.delete(obj);
	}

	public void update(T obj) {
		Session session = sessionFactory.getCurrentSession();
		session.update(obj);
	}

	public T get(Long id) {
		Session session  = sessionFactory.getCurrentSession();
		return (T) session.get(clz, id);
	}

	public List<T> list() {
		Session session = sessionFactory.getCurrentSession();
		return session.createCriteria(clz).list();
	}

	public PageQuery query(BaseQuery bq) {
		Session session = sessionFactory.getCurrentSession();
		String countHql = "SELECT COUNT(obj) FROM "+clz.getSimpleName()+" obj"+bq.getQuery();
		Long totalCount = (Long) setQueryParams(session.createQuery(countHql),bq).uniqueResult();
		if(totalCount <= 0) {
			return new PageQuery(0L,new ArrayList(),bq.getCurrentPage(),bq.getPageSize());
		}
		String queryHql = "SELECT obj FROM "+clz.getSimpleName()+" obj"+bq.getQuery();
		Query query = setQueryParams(session.createQuery(queryHql), bq);
		if(bq.getCurrentPage() > 0&&bq.getPageSize()>0) {
			query.setMaxResults(bq.getPageSize());
			query.setFirstResult((bq.getCurrentPage()-1)*bq.getPageSize());
		}
		return new PageQuery(totalCount,query.list(),bq.getCurrentPage(),bq.getPageSize());
	}
	
	public Query setQueryParams(Query query,BaseQuery bq) {
		for(int i = 0;i<bq.getParams().size();i++) {
			query = query.setParameter(i, bq.getParams().get(i));
		}
		return query;
	}

	public void bathDelete(List<Long> ids) {
		Session session = sessionFactory.getCurrentSession();
		String hql = "DELETE FROM "+clz.getSimpleName()+" obj WHERE id IN(:ids)";
		Query query = session.createQuery(hql).setParameterList("ids", ids);
		query.executeUpdate();
	}
}

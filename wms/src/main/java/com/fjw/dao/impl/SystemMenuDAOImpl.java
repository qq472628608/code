package com.fjw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;

import com.fjw.dao.ISystemMenuDAO;
import com.fjw.domain.SystemMenu;

public class SystemMenuDAOImpl extends GeneratorDAOImpl<SystemMenu> implements ISystemMenuDAO{

	public List<SystemMenu> childrenMenus() {
		Session session = super.sessionFactory.getCurrentSession();
		String hql = "SELECT obj FROM SystemMenu obj WHERE obj.parent IS NOT NULL";
		return session.createQuery(hql).list();
	}

	public List<SystemMenu> getChildrenList(String parentSn) {
		Session session = super.sessionFactory.getCurrentSession();
		Query query = session.createQuery("SELECT obj FROM SystemMenu obj WHERE obj.parent.sn=?");
		query.setParameter(0, parentSn);
		return query.list();
	}

}

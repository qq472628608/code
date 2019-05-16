package com.fjw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;

import com.fjw.Vo.SaleChartType;
import com.fjw.Vo.SaleChartVo;
import com.fjw.dao.ISaleChartDAO;
import com.fjw.query.SaleChartQuery;

import lombok.Setter;

public class SaleChartDAOImpl extends GeneratorDAOImpl<SaleChartVo> implements ISaleChartDAO{
	@Setter
	private SessionFactory sessionFactory;
	public List<SaleChartVo> listSaleChart(SaleChartQuery sqo) {
		Session session = sessionFactory.getCurrentSession();
		SaleChartType type = SaleChartType.valueOf(sqo.getGroupType());
		String hql = "SELECT NEW com.fjw.Vo.SaleChartVo("+type.getVoGroup()+",SUM(obj.number),SUM(obj.saleAmount),SUM(obj.saleAmount-obj.costAmount)) FROM SaleAccount obj"+sqo.getQuery()+" GROUP BY "+type.getGroup();
		Query query = session.createQuery(hql);
		List<Object> params = sqo.getParams();
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i, params.get(i));
		}
		return query.list();
	}
}

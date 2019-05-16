package com.fjw.dao.impl;

import java.util.List;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.Vo.OrderChartType;
import com.fjw.Vo.OrderChartVo;
import com.fjw.dao.IOrderChartDAO;
import com.fjw.query.OrderChartQuery;

import lombok.Setter;

public class OrderChartDAOImpl implements IOrderChartDAO{
	@Setter@Autowired
	private SessionFactory sessionFactory;
	public List<OrderChartVo> orderChartList(OrderChartQuery oqo) {
		Session session = sessionFactory.getCurrentSession();
		OrderChartType type = OrderChartType.valueOf(oqo.getGroupType());
		String hql = "SELECT NEW com.fjw.Vo.OrderChartVo("+type.getVoGroup()+",SUM(obj.number),SUM(obj.amount)) FROM OrderBillItem obj"+oqo.getQuery()+" GROUP BY "+type.getGroup();
		Query query = session.createQuery(hql);
		List<Object> params = oqo.getParams();
		for (int i = 0; i < params.size(); i++) {
			query.setParameter(i, params.get(i));
		}
		return query.list();
	}
}

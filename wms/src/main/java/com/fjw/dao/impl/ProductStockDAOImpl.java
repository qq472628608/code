package com.fjw.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.fjw.dao.IProductStockDAO;
import com.fjw.domain.ProductStock;

public class ProductStockDAOImpl extends GeneratorDAOImpl<ProductStock> implements IProductStockDAO{

	public ProductStock getProductStockByProductAndDepot(Long productId, Long depotId) {
		String hql ="SELECT obj FROM ProductStock obj WHERE obj.product.id=? AND obj.depot.id=?";
		Session session = super.sessionFactory.getCurrentSession();
		Query query = session.createQuery(hql);
		query.setParameter(0, productId);
		query.setParameter(1, depotId);
		return (ProductStock) query.uniqueResult();
	}

}

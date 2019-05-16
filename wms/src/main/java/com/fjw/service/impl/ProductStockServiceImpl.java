package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.IProductStockDAO;
import com.fjw.domain.ProductStock;
import com.fjw.query.PageQuery;
import com.fjw.query.ProductStockQuery;
import com.fjw.service.IProductStockService;

import lombok.Setter;

public class ProductStockServiceImpl implements IProductStockService{
	@Setter@Autowired
	private IProductStockDAO productStockDAO;
	public void save(ProductStock productStock) {
		productStockDAO.save(productStock);
	}

	public void update(ProductStock productStock) {
		productStockDAO.update(productStock);
	}

	public void delete(Long id) {
		productStockDAO.delete(id);
	}

	public ProductStock get(Long id) {
		return productStockDAO.get(id);
	}

	public List<ProductStock> list() {
		return productStockDAO.list();
	}

	public PageQuery query(ProductStockQuery bq) {
		return productStockDAO.query(bq);
	}
}

package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.IProductDAO;
import com.fjw.domain.Product;
import com.fjw.query.PageQuery;
import com.fjw.query.ProductQuery;
import com.fjw.service.IProductService;

import lombok.Setter;

public class ProductServiceImpl implements IProductService{
	@Setter@Autowired
	private IProductDAO productDAO;
	public void save(Product product) {
		productDAO.save(product);
	}

	public void update(Product product) {
		productDAO.update(product);
	}

	public void delete(Long id) {
		productDAO.delete(id);
	}

	public Product get(Long id) {
		return productDAO.get(id);
	}

	public List<Product> list() {
		return productDAO.list();
	}

	public PageQuery query(ProductQuery bq) {
		return productDAO.query(bq);
	}

	public void bathDelete(List<Long> ids) {
		productDAO.bathDelete(ids);
	}

}

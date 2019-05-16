package com.fjw.service;

import java.util.List;

import com.fjw.domain.Product;
import com.fjw.query.PageQuery;
import com.fjw.query.ProductQuery;

public interface IProductService {
	public void save(Product product);
	public void update(Product product);
	public void delete(Long id);
	public Product get(Long id);
	public List<Product> list();
	public PageQuery query(ProductQuery bq);
	public void bathDelete(List<Long> ids);
}

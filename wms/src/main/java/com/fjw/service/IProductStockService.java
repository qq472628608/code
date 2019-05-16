package com.fjw.service;

import java.util.List;

import com.fjw.domain.ProductStock;
import com.fjw.query.PageQuery;
import com.fjw.query.ProductStockQuery;

public interface IProductStockService {
	public void save(ProductStock productStock);
	public void update(ProductStock productStock);
	public void delete(Long id);
	public ProductStock get(Long id);
	public List<ProductStock> list();
	public PageQuery query(ProductStockQuery bq);
}

package com.fjw.service;

import java.util.List;

import com.fjw.domain.ProductKind;
import com.fjw.domain.query.PageResult;

public interface IProductKindService {
	public void save(ProductKind productKind);
	public void delete(Long id);
	public List<ProductKind> list();
	public void update(ProductKind productKind);
	public void batchDelete(Long[] ids);
	public PageResult<ProductKind> list(String name,Integer current);
	public ProductKind get(Long id);
}

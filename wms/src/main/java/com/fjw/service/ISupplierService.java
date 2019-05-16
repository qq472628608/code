package com.fjw.service;

import java.util.List;

import com.fjw.domain.Supplier;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;

public interface ISupplierService {
	public void save(Supplier supplier);
	public void update(Supplier supplier);
	public void delete(Long id);
	public Supplier get(Long id);
	public List<Supplier> list();
	public PageQuery query(BaseQuery bq);
	public void bathDelete(List<Long> ids);
}

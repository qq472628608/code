package com.fjw.service;

import java.util.List;

import com.fjw.domain.Brand;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;

public interface IBrandService {
	public void save(Brand brand);
	public void update(Brand brand);
	public void delete(Long id);
	public Brand get(Long id);
	public List<Brand> list();
	public PageQuery query(BaseQuery bq);
	public void bathDelete(List<Long> ids);
}

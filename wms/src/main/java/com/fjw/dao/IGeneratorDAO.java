package com.fjw.dao;

import java.util.List;

import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;

public interface IGeneratorDAO<T> {
	public void save(T obj);
	public void delete(Long id);
	public void update(T obj);
	public T get(Long id);
	public List<T> list();
	public PageQuery query(BaseQuery bq);
	public void bathDelete(List<Long> ids);
}

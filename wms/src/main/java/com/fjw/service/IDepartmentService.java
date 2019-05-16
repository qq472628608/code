package com.fjw.service;

import java.util.List;

import com.fjw.domain.Department;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;

public interface IDepartmentService {
	public void save(Department dept);
	public void update(Department dept);
	public void delete(Long id);
	public Department get(Long id);
	public List<Department> list();
	public PageQuery query(BaseQuery dq);
}

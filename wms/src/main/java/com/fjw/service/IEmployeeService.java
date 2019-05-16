package com.fjw.service;

import java.util.List;

import com.fjw.domain.Employee;
import com.fjw.query.EmployeeQuery;
import com.fjw.query.PageQuery;

public interface IEmployeeService {
	public void save(Employee e);
	public void update(Employee e);
	public void delete(Long id);
	public Employee get(Long id);
	public List<Employee> list();
	public PageQuery query(EmployeeQuery emq);
	public void bathDelete(List<Long> ids);
	public void checkLogin(String name,String password);
}
 
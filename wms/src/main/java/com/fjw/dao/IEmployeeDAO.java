package com.fjw.dao;

import com.fjw.domain.Employee;

public interface IEmployeeDAO extends IGeneratorDAO<Employee>{
	public Employee checkLogin(String name,String password);
}

package com.fjw.dao.impl;

import org.hibernate.Query;
import org.hibernate.Session;

import com.fjw.dao.IEmployeeDAO;
import com.fjw.domain.Employee;

public class EmployeeDAOImpl extends GeneratorDAOImpl<Employee> implements IEmployeeDAO{

	public Employee checkLogin(String name, String password) {
		Session session = super.sessionFactory.getCurrentSession();
		String Hql = "SELECT obj FROM Employee obj WHERE obj.name=? AND obj.password=?";
		Query query = session.createQuery(Hql);
		query.setParameter(0, name);
		query.setParameter(1, password);
		Employee employee = (Employee) query.uniqueResult();
		return employee;
	}
}

package com.fjw.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.impl.EmployeeDAOImpl;
import com.fjw.domain.Employee;
import com.fjw.domain.Permission;
import com.fjw.domain.Role;
import com.fjw.query.EmployeeQuery;
import com.fjw.query.PageQuery;
import com.fjw.service.IEmployeeService;
import com.fjw.util.UserContext;

import lombok.Setter;

public class EmployeeServiceImpl implements IEmployeeService{
	@Setter@Autowired
	private EmployeeDAOImpl employeeDAO;
	public void save(Employee e) {
		employeeDAO.save(e);
	}

	public void update(Employee e) {
		employeeDAO.update(e);
	}

	public void delete(Long id) {
		employeeDAO.delete(id);
	}

	public Employee get(Long id) {
		return employeeDAO.get(id);
	}

	public List<Employee> list() {
		return employeeDAO.list();
	}

	public PageQuery query(EmployeeQuery emq) {
		return employeeDAO.query(emq);
	}

	public void bathDelete(List<Long> ids) {
		employeeDAO.bathDelete(ids);
	}

	public void checkLogin(String name, String password) {
		Employee current = employeeDAO.checkLogin(name, password);
		if(current == null) {
			throw new RuntimeException("账号或密码错误!");
		}
		UserContext.putCurrentEmployee(current);
		List<String> expressions = new ArrayList<>();
		Set<Role> roles = current.getRoles();
		for (Role role : roles) {
			Set<Permission> permissions = role.getPermissions();
			for (Permission permission : permissions) {
				expressions.add(permission.getExpression());
			}
		}
		UserContext.putCurrentExpression(expressions);
	}
}

package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.impl.DepartmentDAOImpl;
import com.fjw.domain.Department;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;
import com.fjw.service.IDepartmentService;

import lombok.Setter;

public class DepartmentServiceImpl implements IDepartmentService{
	@Setter@Autowired
	private DepartmentDAOImpl departmentDAO;
	public void save(Department dept) {
		departmentDAO.save(dept);
	}

	public void update(Department dept) {
		departmentDAO.update(dept);
	}

	public void delete(Long id) {
		departmentDAO.delete(id);
	}

	public Department get(Long id) {
		return departmentDAO.get(id);
	}

	public List<Department> list() {
		return departmentDAO.list();
	}
	
	public PageQuery query(BaseQuery dq) {
		return departmentDAO.query(dq);
	}

}

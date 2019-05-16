package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.IRoleDAO;
import com.fjw.domain.Role;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;
import com.fjw.service.IRoleService;

import lombok.Setter;

public class RoleServiceImpl implements IRoleService{
	@Setter@Autowired
	private IRoleDAO roleDAO;
	public void save(Role role) {
		roleDAO.save(role);
	}

	public void update(Role role) {
		roleDAO.update(role);
	}

	public void delete(Long id) {
		roleDAO.delete(id);
	}

	public List<Role> list() {
		return roleDAO.list();
	}

	public PageQuery query(BaseQuery bq) {
		return roleDAO.query(bq);
	}

	public void bathDelete(List<Long> ids) {
		roleDAO.bathDelete(ids);
	}

	public Role get(Long id) {
		return roleDAO.get(id);
	}
}

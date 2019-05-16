package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.impl.PermissionDAOImpl;
import com.fjw.domain.Permission;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;
import com.fjw.service.IPermissionService;

import lombok.Setter;

public class PermissionServiceImpl implements IPermissionService{
	@Setter@Autowired
	private PermissionDAOImpl permissionDAO;
	public PageQuery query(BaseQuery bq) {
		return permissionDAO.query(bq);
	}

	public void delete(Long id) {
		permissionDAO.delete(id);
	}

	public void reload() {
		permissionDAO.reload();
	}

	public void bathDelete(List<Long> ids) {
		permissionDAO.bathDelete(ids);
	}

	public List<Permission> list() {
		return permissionDAO.list();
	}
}

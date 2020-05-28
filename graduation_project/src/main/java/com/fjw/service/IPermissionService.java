package com.fjw.service;

import java.util.List;

import com.fjw.domain.Permission;
import com.fjw.domain.Role;

public interface IPermissionService {
	public void reload();
	public List<Permission> list();
	public List<Role> listRoleByPermission(Long permissionId);
}

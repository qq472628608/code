package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.Permission;
import com.fjw.domain.Role;
import com.fjw.mapper.PermissionMapper;
import com.fjw.service.IPermissionService;

import lombok.Setter;

@Service
public class PermissionServiceImpl implements IPermissionService {
	
	@Setter@Autowired@Lazy
	private PermissionMapper permissionMapper;

	public void reload() {
		
	}
	
	@Cacheable(value="permission")
	@LogAnnotation(description="获取所有权限")
	public List<Permission> list() {
		return permissionMapper.list();
	}

	@Cacheable(value="permission")
	@LogAnnotation(description="获取拥有权限的角色")
	public List<Role> listRoleByPermission(Long permissionId) {
		return permissionMapper.listByPermission(permissionId);
	}

}

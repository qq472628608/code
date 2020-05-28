package com.fjw.mapper;


import org.apache.ibatis.annotations.Mapper;

import com.fjw.domain.RolePermission;

@Mapper
public interface RolePermissionMapper {
	public void saveRolePermission(RolePermission rolePermission);
	public void deleteRolePermission(Long id);
	public RolePermission getRolePermission(Long id);
	public void updateRolePermission(RolePermission rolePermission);
}

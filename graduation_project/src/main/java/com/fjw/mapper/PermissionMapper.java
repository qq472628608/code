package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fjw.domain.Permission;
import com.fjw.domain.Role;

@Mapper
public interface PermissionMapper {
	public List<Permission> list();
	public List<Role> listByPermission(Long permission_id);
}

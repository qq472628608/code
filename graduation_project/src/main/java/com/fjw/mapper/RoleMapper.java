package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fjw.domain.Permission;
import com.fjw.domain.Role;

@Mapper
public interface RoleMapper {
	public void saveRole(Role role);
	public void deleteRole(Long id);
	public Role getRole(Long id);
	public void updateRole(Role role);
	public List<Role> list();
	//public List<Role> listByUser(Long user_id);
	public List<Permission> listByRole(Long id);
	public Role queryByName(String name);
	public void batchDelete(Long[] ids);
}

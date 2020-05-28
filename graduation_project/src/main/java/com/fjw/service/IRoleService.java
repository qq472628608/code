package com.fjw.service;

import java.util.List;


import com.fjw.domain.Role;

public interface IRoleService {
	public void saveRole(Role role) throws Exception;
	public void deleteRole(Long id);
	public Role getRole(Long id);
	public void updateRole(Role role) throws Exception;
	public List<Role> list();
	public Role queryByName(String name);
	public void bathDelete(Long[] ids);
}

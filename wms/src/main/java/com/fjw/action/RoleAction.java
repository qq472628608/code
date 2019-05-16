package com.fjw.action;


import java.util.List;

import com.fjw.domain.Role;
import com.fjw.query.BaseQuery;
import com.fjw.service.IPermissionService;
import com.fjw.service.IRoleService;
import com.fjw.service.ISystemMenuService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class RoleAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private IRoleService roleService;
	@Setter
	private IPermissionService permissionService;
	@Setter
	private ISystemMenuService systemMenuService;
	@Setter@Getter
	private BaseQuery bq = new BaseQuery();
	@Setter@Getter
	private Role role = new Role();
	@Setter@Getter
	private List<Long> ids;
	@RequiredPermission("角色列表")
	@InputConfig(methodName="edit")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", roleService.query(bq));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	
	@RequiredPermission("角色编辑")
	public String edit() throws Exception{
		ActionContext.getContext().put("menus", systemMenuService.childrenMenus());
		ActionContext.getContext().put("permissions", permissionService.list());
		if(role.getId() != null) {
			role = roleService.get(role.getId());
		}
		return "edit";
	}
	
	@RequiredPermission("角色保存或更新")
	public String saveOrUpdate() {
		try {
			if(role.getId() == null) {
				roleService.save(role);
			}else {
				roleService.update(role);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	
	@RequiredPermission("角色删除")
	public String delete() throws Exception{
		roleService.delete(role.getId());
		return NONE;
	}
	
	@RequiredPermission("角色批量删除")
	public String bathDelete() {
		roleService.bathDelete(ids);
		return NONE;
	}
	
	public void prepareSaveOrUpdate() {
		this.role.getPermissions().clear();
		this.role.getSystemMenus().clear();
	}
}

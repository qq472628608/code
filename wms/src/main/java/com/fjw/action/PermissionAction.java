package com.fjw.action;


import java.util.List;

import com.fjw.domain.Permission;
import com.fjw.query.BaseQuery;
import com.fjw.service.IPermissionService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;

import lombok.Getter;
import lombok.Setter;

public class PermissionAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private IPermissionService permissionService;
	@Setter@Getter
	private BaseQuery bq = new BaseQuery();
	@Setter@Getter
	private Permission permission= new Permission();
	@Setter@Getter
	private List<Long> ids;
	
	@RequiredPermission("权限列表")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", permissionService.query(bq));			
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	@RequiredPermission("权限删除")
	public String delete() throws Exception{
		permissionService.delete(permission.getId());
		return NONE;
	}
	@RequiredPermission("权限加载")
	public String reload() throws Exception{
		permissionService.reload();
		return "reload";
	}
	@RequiredPermission("权限批量删除")
	public String bathDelete() throws Exception{
		permissionService.bathDelete(ids);
		return NONE;
	}
}

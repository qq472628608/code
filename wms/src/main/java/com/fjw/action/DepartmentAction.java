package com.fjw.action;

import com.fjw.domain.Department;
import com.fjw.query.BaseQuery;
import com.fjw.service.IDepartmentService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class DepartmentAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
    private IDepartmentService departmentService;
	@Setter@Getter
	private BaseQuery bq = new BaseQuery();
	@Setter@Getter
	private Department department = new Department();
	
	@InputConfig(methodName="edit")
	@RequiredPermission("部门列表")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", departmentService.query(bq));
		}catch (Exception e){
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	@RequiredPermission("部门编辑")
	public String edit() throws Exception{
		Long id = department.getId();
		if(id != null) {
			department = departmentService.get(id);
		}
		return "edit";
	}
	@RequiredPermission("部门保存或更新")
	public String saveOrUpdate() {
		try {
			if(department.getId() == null) {
				departmentService.save(department);
			}else {
				departmentService.update(department);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	@RequiredPermission("部门删除")
	public String delete() throws Exception{
		departmentService.delete(department.getId());
		return NONE;
	}	
}

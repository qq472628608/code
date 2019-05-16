package com.fjw.action;


import java.util.List;


import com.fjw.domain.Employee;
import com.fjw.query.EmployeeQuery;
import com.fjw.service.IDepartmentService;
import com.fjw.service.IEmployeeService;
import com.fjw.service.IRoleService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class EmployeeAction extends BaseAction{
	@Setter
	private IEmployeeService employeeService;
	@Setter
	private IDepartmentService departmentService;
	@Setter
	private IRoleService roleService;
	@Setter@Getter
	private EmployeeQuery bq = new EmployeeQuery();
	@Setter@Getter
	private Employee employee = new Employee();
	@Setter
	private List<Long> ids;
	private static final long serialVersionUID = 1L;
	@InputConfig(methodName="edit")
	@RequiredPermission("员工列表")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", employeeService.query(bq));
			ActionContext.getContext().put("depts", departmentService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	@RequiredPermission("员工编辑")
	public String edit() throws Exception{
		ActionContext.getContext().put("roles", roleService.list());
		ActionContext.getContext().put("depts", departmentService.list());
		Long id = employee.getId();
		if(id != null) {
			employee = employeeService.get(id);
		}
		return "edit";
	}
	@RequiredPermission("员工保存或更新")
	public String saveOrUpdate(){
		try {
			if(employee.getId() == null) {
				employeeService.save(employee);
			}else {
				employeeService.update(employee);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	@RequiredPermission("员工删除")
	public String delete() throws Exception{
		employeeService.delete(employee.getId());
		return NONE;
	}
	@RequiredPermission("员工批量删除")
	public String bathDelete() throws Exception{
		employeeService.bathDelete(ids);
		return NONE;
	}
	
	public void prepareSaveOrUpdate() throws Exception{
		if(employee.getId() != null) {
			this.employee = employeeService.get(employee.getId());
			employee.setDept(null);
		}
		employee.getRoles().clear();
	}
}

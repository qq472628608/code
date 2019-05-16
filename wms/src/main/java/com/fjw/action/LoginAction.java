package com.fjw.action;



import com.fjw.service.IEmployeeService;

import lombok.Getter;
import lombok.Setter;

public class LoginAction extends BaseAction{
	private static final long serialVersionUID = 1L;
	@Setter
	private IEmployeeService employeeService;
	@Setter@Getter
	private String name;
	@Setter@Getter
	private String password;
	public String execute() {
		try {
			employeeService.checkLogin(name, password);
		} catch (Exception e) {
			addActionError(e.getMessage());
			return "login";
		}
		return SUCCESS;
	}
}

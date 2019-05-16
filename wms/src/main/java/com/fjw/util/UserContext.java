package com.fjw.util;

import java.util.List;

import com.fjw.domain.Employee;
import com.opensymphony.xwork2.ActionContext;

public class UserContext {

	public static void putCurrentEmployee(Employee current) {
		ActionContext.getContext().getSession().put("USER_IN_SESSION", current);	
	}

	public static void putCurrentExpression(List<String> expressions) {
		ActionContext.getContext().getSession().put("EXPRESSION_IN_SESSION", expressions);
	}

	public static Employee getCurrentEmployee() {
		return (Employee) ActionContext.getContext().getSession().get("USER_IN_SESSION");
	}

	public static List<String> getCurrentExpression() {
		return (List<String>) ActionContext.getContext().getSession().get("EXPRESSION_IN_SESSION");
	}
}

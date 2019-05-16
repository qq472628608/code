package com.fjw.interceptor;

import java.util.Map;

import com.fjw.domain.Employee;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class LoginInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
		Map<String,Object> sessionMap = invocation.getInvocationContext().getSession();
		Employee current = (Employee) sessionMap.get("USER_IN_SESSION");
		if(current != null) {
			return invocation.invoke();
		}
		return "login";
	}
}

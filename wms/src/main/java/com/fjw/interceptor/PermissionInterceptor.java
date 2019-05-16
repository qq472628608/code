package com.fjw.interceptor;

import java.lang.reflect.Method;
import java.util.List;


import com.fjw.domain.Employee;
import com.fjw.util.ExpressionUtil;
import com.fjw.util.RequiredPermission;
import com.fjw.util.UserContext;
import com.opensymphony.xwork2.ActionInvocation;
import com.opensymphony.xwork2.interceptor.AbstractInterceptor;

public class PermissionInterceptor extends AbstractInterceptor{

	private static final long serialVersionUID = 1L;

	public String intercept(ActionInvocation invocation) throws Exception {
		Employee current = UserContext.getCurrentEmployee();
		if(current != null && current.getAdmin()) {
			return invocation.invoke();
		}
		if(current != null) {
			String methodName = invocation.getProxy().getMethod();
			Method method = invocation.getProxy().getAction().getClass().getDeclaredMethod(methodName);
			RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
			if(rp == null) {
				return invocation.invoke();
			}else {
				String expression = ExpressionUtil.getExpression(method);
				List<String> expressions = UserContext.getCurrentExpression();
				if(expressions != null && expressions.contains(expression)) {
					return invocation.invoke();
				}
			}
		}
		throw new RuntimeException("您没有权限执行此操作!");
	}

}

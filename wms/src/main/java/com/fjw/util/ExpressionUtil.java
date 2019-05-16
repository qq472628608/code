package com.fjw.util;

import java.lang.reflect.Method;

public class ExpressionUtil {
	public static String getExpression(Method method) {
		String className = method.getDeclaringClass().getName();
		String methodName= method.getName();
		return className+":"+methodName;
	}
}

package com.fjw.aspect;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.fjw.annotation.LogAnnotation;

@Component
@Aspect
public class LogAspect {
	
	private Logger logger = LoggerFactory.getLogger(LogAspect.class);
	
	@AfterThrowing(value="@annotation(com.fjw.annotation.LogAnnotation)",throwing="ex")
	public void logException(JoinPoint joinPoint,Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		logger.error("[description:"+getAnnotationDescription(joinPoint)+",methodName:"+methodName+",ExceptionInfo:"+
		ex.getMessage()+":"+ex.toString());
	}
	
	public String getAnnotationDescription(JoinPoint joinPoint) {
		String description = "";
		String methodName = joinPoint.getSignature().getName();
		Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
		for (Method method : methods) {
			int length = method.getParameterTypes().length;
			if(methodName.equals(method.getName()) && length == joinPoint.getArgs().length) {
				description = method.getAnnotation(LogAnnotation.class).description(); 
			}
		}
		return description;
	}
	
}

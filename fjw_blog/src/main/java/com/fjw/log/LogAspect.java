package com.fjw.log;

import java.lang.reflect.Method;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Component
@Aspect
public class LogAspect {
	private Logger logger = LogManager.getLogger(LogAspect.class);
	
	@AfterThrowing(value="@annotation(com.fjw.log.LogAnnotation)",throwing="ex")
	public void logAfterThrowException(JoinPoint joinPoint,Exception ex) {
		String methodName = joinPoint.getSignature().getName();
		logger.error("description:"+getDescription(joinPoint)+"name:"+methodName+"exceptionMessage:"+ex.getMessage()+
				"exceptionClass"+ex.toString());
	}
	
	public String getDescription(JoinPoint joinPoint) {
		String description = "";
		String methodName = joinPoint.getSignature().getName();
		Method[] methods = joinPoint.getTarget().getClass().getDeclaredMethods();
		for (Method method : methods) {
			int num = method.getParameterTypes().length;
			if(methodName.equals(method.getName())&&num == joinPoint.getArgs().length) {
				description = method.getAnnotation(LogAnnotation.class).description();
			}
		}
		return description;
	}
}

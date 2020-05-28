package com.fjw.util;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import com.fjw.annotation.LogAnnotation;

@Component
public class ApplicationContextHolder implements ApplicationContextAware{
	
	@Autowired
	private static ApplicationContext applicationContext;
	
	/**
	 * implements set method
	 */
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		ApplicationContextHolder.applicationContext = applicationContext;
	}

	/**
	 * get bean by className
	 * @param name
	 * @return
	 * @throws ApplicationContextNotFoundException
	 */
	public static<T> T getBean(String name) {
		checkApplicationContext();
		return (T) applicationContext.getBean(name);
	}
	
	/**
	 * get applicationContext
	 * @return
	 * @throws ApplicationContextNotFoundException
	 */
	public static ApplicationContext getApplicationContext() {
		checkApplicationContext();
		return applicationContext;
	}
	
	/**
	 * get bean by class
	 * @param clz
	 * @return
	 * @throws ApplicationContextNotFoundException
	 */
	public static<T> T getBean(Class<T> clz) {
		checkApplicationContext();
		return applicationContext.getBean(clz);
	}
	
	/**
	 * check applicationContext is existed;
	 * @throws IllegalStateException
	 */
	@LogAnnotation(description = "applicationContext not init")
	public static void checkApplicationContext() {
		if(applicationContext == null) {
			throw new IllegalStateException("applicationContext not init");
		}
	}

}

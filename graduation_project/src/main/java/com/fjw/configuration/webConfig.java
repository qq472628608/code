package com.fjw.configuration;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import com.fjw.security.LoginInterceptor;
import com.fjw.security.PermissionInterceptor;

@Configuration
public class webConfig implements WebMvcConfigurer {
	
	@Autowired
	private LoginInterceptor interceptor;
	@Autowired
	private PermissionInterceptor pInterceptor;
	
	
	public void addViewControllers(ViewControllerRegistry registry) {
		registry.addViewController("/").setViewName("/html/index.html");
	}
	
	public void addInterceptors(InterceptorRegistry registry) {
		InterceptorRegistration addInterceptor = registry.addInterceptor(interceptor);
		addInterceptor.addPathPatterns("/html/*.html");
		addInterceptor.excludePathPatterns("/html/index.html","/html/detail.html","/html/login.html","/html/signup.html","/html/forgetPs.html");
		registry.addInterceptor(pInterceptor).addPathPatterns("/html/manager.html");
	}
}

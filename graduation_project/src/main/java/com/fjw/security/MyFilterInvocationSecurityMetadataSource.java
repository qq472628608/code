/*package com.fjw.security;

import java.util.Collection;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Component;
import org.springframework.util.AntPathMatcher;

import com.fjw.domain.Permission;
import com.fjw.domain.Role;
import com.fjw.service.IPermissionService;

import lombok.Setter;

import org.springframework.security.web.FilterInvocation;

@Component
public class MyFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {
	
	@Setter@Autowired
	private IPermissionService permissionService;
	
	private AntPathMatcher antPathMathcher = new AntPathMatcher();
	
	*//**
	 * 返回需要的权限的角色列表
	 *//*
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {
		String requestUrl = ((FilterInvocation)object).getRequestUrl();
		List<Permission> permissions = permissionService.list();
		for (Permission permission : permissions) {
			if(antPathMathcher.match(permission.getSn(), requestUrl) && permission.getRoles().size() > 0) {
				List<Role> roles = permission.getRoles();
				int size = roles.size();
				String[] values = new String[size];
				for (int i = 0; i < size; i++) {
					values[i] = roles.get(i).getName();
				}
				return SecurityConfig.createList(values);
			}
		}
		//不需要权限
		return null;
	}
	
	*//**
	 * 如果实现了这个方法会返回权限资源列表
	 *//*
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		return null;
	}
	
	*//**
	 * 返回对象是否支持校验
	 *//*
	public boolean supports(Class<?> clazz) {
		return FilterInvocation.class.isAssignableFrom(clazz);
	}

}
*/
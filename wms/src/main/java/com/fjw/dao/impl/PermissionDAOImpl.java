package com.fjw.dao.impl;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;

import com.fjw.action.BaseAction;
import com.fjw.dao.IPermissionDAO;
import com.fjw.domain.Permission;
import com.fjw.util.ExpressionUtil;
import com.fjw.util.RequiredPermission;

import lombok.Setter;

public class PermissionDAOImpl extends GeneratorDAOImpl<Permission> implements IPermissionDAO{
	
	@Setter@Autowired
	private ApplicationContext ctx;
	
	public void reload() {
		List<Permission> permissions= this.list();
		List<String> expressions = new ArrayList<String>();
		for (Permission permission : permissions) {
			expressions.add(permission.getExpression());
		}
		Map<String, BaseAction> actionsMap = ctx.getBeansOfType(BaseAction.class);
		Collection<BaseAction> actions = actionsMap.values();
		for (BaseAction baseAction : actions) {
			Method[] methods = baseAction.getClass().getDeclaredMethods();
			for (Method method : methods) {
				RequiredPermission rp = method.getAnnotation(RequiredPermission.class);
				if(rp != null) {
					String name = rp.value();
					String expression = ExpressionUtil.getExpression(method);
					if(!expressions.contains(expression)) {
						Permission permission = new Permission();
						permission.setName(name);
						permission.setExpression(expression);
						this.save(permission);
					}
				}
			}
		}
	}	
}

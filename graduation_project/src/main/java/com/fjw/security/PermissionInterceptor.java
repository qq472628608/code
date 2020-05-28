package com.fjw.security;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.fjw.domain.User;

@Component
public class PermissionInterceptor extends HandlerInterceptorAdapter {

	@Autowired
	private RedisTemplate redisTemplate;
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("tk".equals(cookie.getName())) {
				User user = (User) redisTemplate.opsForValue().get(cookie.getValue());
				if(user != null) {
					String roleName = user.getRole().getName();
					if("超级管理员".equals(roleName) || "管理员".equals(roleName)){
						//response.sendRedirect("/html/manager.html");
						return true;
					}
				}
				break;
			}
		}
		response.sendRedirect("/html/login.html");
		return false;
	}
}

package com.fjw.util;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.springframework.data.redis.core.RedisTemplate;

import com.fjw.domain.User;

public class ContextUserUtil {
	
	private static RedisTemplate redisTemplate = ApplicationContextHolder.getBean("redisTemplate");
	
	public static User getCurrentUser(HttpServletRequest request) {
		Cookie[] cookies = ThreadLocalHandler.getLocalRequest().getCookies();
		for (Cookie cookie : cookies) {
			if("tk".equals(cookie.getName())) {
				String token = cookie.getValue();
				User user = (User) redisTemplate.opsForValue().get(token);
				if(user != null) {
					return user;
				}else {
					redisTemplate.delete(token);
				}
				break;
			}
		}
		return null;
	}
	
}

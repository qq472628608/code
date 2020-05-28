package com.fjw.util;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

public class ThreadLocalHandler {
	private static ThreadLocal<HttpServletRequest> requestThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<HttpServletResponse> responseThreadLocal = new ThreadLocal<>();
	private static ThreadLocal<HttpSession> sessionThreadLocal = new ThreadLocal<>();
	
	public static void setLocalRequest(HttpServletRequest request) {
		requestThreadLocal.set(request);
	}
	
	public static HttpServletRequest getLocalRequest() {
		return requestThreadLocal.get();
	}
	
	public static void setLocalResponse(HttpServletResponse response) {
		responseThreadLocal.set(response);
	}
	
	public static HttpServletResponse getLocalResponse() {
		return responseThreadLocal.get();
	}
	
	public static void setLocalSession(HttpSession session) {
		session.setMaxInactiveInterval(60*60);
		sessionThreadLocal.set(session);
	}
	
	public static HttpSession getLocalSession() {
		return sessionThreadLocal.get();
	}
	
	public static void setAll(HttpServletRequest request,HttpServletResponse response) {
		HttpSession session = request.getSession();
		session.setMaxInactiveInterval(60*60);
		requestThreadLocal.set(request);
		responseThreadLocal.set(response);
		sessionThreadLocal.set(session);
	}
}

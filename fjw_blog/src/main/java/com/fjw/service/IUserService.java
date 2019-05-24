package com.fjw.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.fjw.domain.User;

public interface IUserService {
	public String cookieContent(HttpServletRequest request);
	public void login(String username,String password,Boolean remPassword,HttpServletRequest request,HttpServletResponse response);
	public void regist(String username,String password,String identifyCode,HttpServletRequest request);
	public void forgetPassword(String username,String password,String identifyCode,HttpServletRequest request);
	public User getOtherInfo(Long id);
	public void updateUserInfo(User user,HttpServletRequest request);
	public String getIdentifyCode(String target,HttpServletRequest request);
	public String getCurrentUserId(HttpServletRequest request);
}

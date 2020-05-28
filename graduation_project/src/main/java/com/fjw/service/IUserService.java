package com.fjw.service;


import org.springframework.stereotype.Service;

import com.fjw.domain.User;
import com.fjw.domain.query.PageResult;
import com.fjw.domain.query.UserQuery;


@Service
public interface IUserService {
	
	public User checkLogin() throws Exception;
	
	public void login(String username,String password) throws Exception;
	
	public void loginByIdentifyCode(String username,String identifyCode) throws Exception;
	
	public void signup(String username,String password,String identifyCode) throws Exception;
	
	public User update(User user) throws Exception;
	
	public void updatePassword(String username,String identifyCode,String newPassword) throws Exception;
	
	public void exit();
	
	public PageResult<User> queryUser(UserQuery userQuery);
	
	public void delete(Long id);
}

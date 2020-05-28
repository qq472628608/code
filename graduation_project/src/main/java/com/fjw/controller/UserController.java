package com.fjw.controller;


import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.User;
import com.fjw.domain.query.PageResult;
import com.fjw.domain.query.UserQuery;
import com.fjw.service.IUserService;
import com.fjw.util.ThreadLocalHandler;

import lombok.Setter;

@RestController
public class UserController {
	
	@Setter@Autowired
	private IUserService userService;
	
	@RequestMapping(value="/user",method=RequestMethod.POST)
	public void saveUser(String username,String password,String identifyCode,HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ThreadLocalHandler.setAll(request, response);
		userService.signup(username, password, identifyCode);
	}

	@RequestMapping(value = "/user",method=RequestMethod.GET)
	public User checkLogin(HttpServletRequest request,HttpServletResponse response) throws Exception {
		ThreadLocalHandler.setAll(request, response);
		User user = userService.checkLogin();
		return user;
	}
	
	@RequestMapping(value="/user",method=RequestMethod.PUT)
	public User update(@RequestBody User user,HttpServletRequest request) throws Exception {
		ThreadLocalHandler.setLocalRequest(request);
		return userService.update(user);
	}
	
	@RequestMapping(value="/user/password",method=RequestMethod.PUT)
	public void updatePassword(String username,String identifyCode,String newPassword,HttpServletRequest reqeust) throws Exception {
		ThreadLocalHandler.setLocalRequest(reqeust);
		userService.updatePassword(username, identifyCode, newPassword);
	}
	
	@RequestMapping(value="/users",method=RequestMethod.PUT)
	public PageResult<User> query(UserQuery query){
		return userService.queryUser(query);
	}
	
	@RequestMapping(value="/user/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("id")Long id) {
		userService.delete(id);
	}
	
}

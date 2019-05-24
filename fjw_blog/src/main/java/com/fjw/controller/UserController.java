package com.fjw.controller;

import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.alibaba.fastjson.JSON;
import com.fjw.domain.User;
import com.fjw.service.IUserService;
import com.fjw.util.FileUtil;

import lombok.Setter;

@Controller
public class UserController {
	@Setter@Autowired
	private IUserService userService;
	@RequestMapping(value="/login",produces="text/html;charset=utf-8")
	@ResponseBody
	public String login(String username,String password,Boolean remPassword,HttpServletRequest request,HttpServletResponse response) {
		try {
			userService.login(username, password,remPassword,request,response);
		} catch (Exception e) {
			e.printStackTrace();
			return e.getMessage();
		}
		return null;
	}
	
	@RequestMapping("/cookieContent")
	@ResponseBody
	public String cookieContent(HttpServletRequest request) {
			return userService.cookieContent(request);
	}
	
	@RequestMapping("/identifyCode")
	@ResponseBody
	public String getIdentifyCode(String target,HttpServletRequest request) {
		return userService.getIdentifyCode(target,request);
	}
	
	@RequestMapping(value="/regist",produces="text/html;charset=utf-8;")
	@ResponseBody
	public String regist(String username,String password,String identifyCode,HttpServletRequest request) {
		if(identifyCode == ""||identifyCode == null) {
			return "验证码错误";
		}else {
			try {
				userService.regist(username, password, identifyCode,request);
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
		}
		return null;
	}
	
	@RequestMapping(value="/forgetPassword",produces="text/html;charset=utf-8;")
	@ResponseBody
	public String forgetPassword(String username,String password,String identifyCode,HttpServletRequest request) {
		if(identifyCode == ""||identifyCode == null) {
			return "验证码错误";
		}else {
			try {
				userService.forgetPassword(username, password, identifyCode,request);
			} catch (Exception e) {
				e.printStackTrace();
				return e.getMessage();
			}
		}
		return null;
	}
	@RequestMapping("/updateUserInfo")
	public void updateUserInfo(User user,HttpServletRequest request,HttpServletResponse response) {
		try {
			userService.updateUserInfo(user,request);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	@RequestMapping(value="/getInfo",produces="text/html;charset=utf-8;")
	@ResponseBody
	public String getInfo(Long id) {
		return JSON.toJSONString(userService.getOtherInfo(id)); 
	}
	
	@RequestMapping("/currentUserId")
	@ResponseBody
	public String getCurrentUserId(HttpServletRequest request) {
		return userService.getCurrentUserId(request);
	}
	
	@RequestMapping("/uploadMyFile")
	@ResponseBody
	public String upload(@RequestParam("file")MultipartFile file,HttpServletRequest request) {
		String oldName = file.getOriginalFilename();
		InputStream in;
		Map<String,String> map = new HashMap<>();
		try {
			in = file.getInputStream();
			FileUtil.upload(in, oldName,request);
			map.put("data", "./upload/"+request.getSession().getAttribute("IMAGE_NAME"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return JSON.toJSONString(map);
	} 
	
}

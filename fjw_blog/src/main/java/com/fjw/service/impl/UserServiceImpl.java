package com.fjw.service.impl;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.fjw.domain.User;
import com.fjw.log.LogAnnotation;
import com.fjw.mail.MailUtil;
import com.fjw.mapper.UserMapper;
import com.fjw.service.IUserService;
import com.fjw.util.IdentifyCodeUtil;
import com.fjw.util.StringUtil;

import lombok.Setter;
@Service
public class UserServiceImpl implements IUserService{
	@Setter@Autowired
	private UserMapper userDAO;
	
	//is cookie has user
	@LogAnnotation(description="获取cookie中存储的账号密码")
	public String cookieContent(HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		if(cookies != null) {
			for (Cookie cookie : cookies) {
				if("username".equals(cookie.getName())) {
					User user = userDAO.query(cookie.getValue());
					return JSON.toJSONString(user);
				}
			}
		}
		return null;
	}
	
	//loginService
	@LogAnnotation(description="登陆")
	public void login(String username, String password,Boolean remPassword,HttpServletRequest request,HttpServletResponse response) {
		User userInSession = (User) request.getSession().getAttribute("USER_IN_SESSION");
		if(userInSession == null) {
			User user = userDAO.query(username);
			if(user == null) {
				throw new RuntimeException("用户不存在");
			}else {
				if(!user.getPassword().equals(password)) {
					throw new RuntimeException("密码错误");
				}else {
					if(remPassword != null) {
						if(remPassword) {
							Cookie cookie = new Cookie("username",username);
							cookie.setMaxAge(604800);
							cookie.setHttpOnly(true);
							response.addCookie(cookie);
						}
					}
					User user2 = userDAO.query(username);
					request.getSession().setAttribute("USER_IN_SESSION", user2);
				}
			}
		}else {
			if(userInSession.getUsername().equals(username)) {
				throw new RuntimeException("用户已登录");
			}
		}
	}
	
	//registService
	@LogAnnotation(description="注册")
	public void regist(String username,String password,String identifyCode,HttpServletRequest request) {
		if(identifyCode.equals(request.getSession().getAttribute("IDENTIFY_CODE"))) {
			User user = userDAO.query(username);
			if(user == null) {
				user = new User();
				user.setUsername(username);
				user.setPassword(password);
				user.setName("游客");
				user.setAge(null);
				user.setSchool(null);
				user.setGender(1);
				HttpSession session = request.getSession();
				if(session != null) {
					user.setImage("./image/head/man1.png");
					userDAO.save(user);
					User user2 = userDAO.query(username);
					session.setAttribute("USER_IN_SESSION", user2);
				}
			}else {
				throw new RuntimeException("账号已存在");
			}
		}else {
			throw new RuntimeException("验证码错误");
		}
	}
	
	//forget password
	@LogAnnotation(description="忘记密码")
	public void forgetPassword(String username,String password,String identifyCode,HttpServletRequest request) {
		if(identifyCode.equals(request.getSession().getAttribute("IDENTIFY_CODE"))) {
			User user = userDAO.query(username);
			if(user == null) {
				throw new RuntimeException("账号不存在");
			}else {
				user.setPassword(password);
				userDAO.update(user);
				request.getSession().setAttribute("USER_IN_SESSION", user);
			}
		}else {
			throw new RuntimeException("验证码错误");
		}
	}
	
	//look other info
	@LogAnnotation(description="获取user信息")
	public User getOtherInfo(Long id) {
		return userDAO.get(id);
	}
	//update userinfo
	@LogAnnotation(description="设置user信息")
	public void updateUserInfo(User user,HttpServletRequest request) {
		User sessionUser = (User) request.getSession().getAttribute("USER_IN_SESSION");
		if(StringUtil.hasLength(user.getImage())) {
			sessionUser.setImage("./image/head/"+user.getImage());
		}else {
			sessionUser.setImage("./upload/"+(String)request.getSession().getAttribute("IMAGE_NAME"));
		}
		sessionUser.setName(user.getName());
		sessionUser.setAge(user.getAge());
		sessionUser.setSchool(user.getSchool());
		sessionUser.setGender(user.getGender());
		userDAO.update(sessionUser);
	}
	
	//return current userid
	@LogAnnotation(description="获取当前用户id")
	public String getCurrentUserId(HttpServletRequest request) {
		if(request != null) {
			User user = (User) request.getSession().getAttribute("USER_IN_SESSION");
			if(user != null) {
				return user.getId()+"";
			}
		}
		return null;
	}
	//return identifycode
	@LogAnnotation(description="获取验证码")
	public String getIdentifyCode(String target,HttpServletRequest request) {
		String identifyCode = IdentifyCodeUtil.getIndetifyCode();
		request.getSession().setAttribute("IDENTIFY_CODE", identifyCode);
		MailUtil.sendMail(target, "欢迎注册FjwBlog", "您的验证码为："+identifyCode);
		return identifyCode;
	}

}


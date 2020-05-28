package com.fjw.service.impl;


import java.io.File;
import java.util.List;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.core.io.ClassPathResource;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ResourceUtils;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.ShoppingCar;
import com.fjw.domain.User;
import com.fjw.domain.query.PageResult;
import com.fjw.domain.query.UserQuery;
import com.fjw.exception.MyIllegalArgumentException;
import com.fjw.mapper.UserMapper;
import com.fjw.service.ICarService;
import com.fjw.service.IRoleService;
import com.fjw.service.IUserService;
import com.fjw.util.ContextUserUtil;
import com.fjw.util.CryptUtil;
import com.fjw.util.StringUtil;
import com.fjw.util.ThreadLocalHandler;

import lombok.Setter;

@Service
public class UserServiceImpl implements IUserService{

	@Setter@Autowired@Lazy
	private UserMapper userMapper;
	@Setter@Autowired@Lazy
	private IRoleService roleService;
	@Setter@Autowired
	private RedisTemplate redisTemplate;
	@Setter@Autowired
	private ICarService carService;
	
	/**
	 * check login 
	 * check cookie if the token in cookie is correctly login without username and password
	 * @throws Exception 
	 */
	@LogAnnotation(description="检查登陆")
	public User checkLogin() throws Exception {
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
	
	/**
	 * login with username and password
	 */
	@LogAnnotation(description="用户名登陆")
	public void login(String username, String password) throws Exception {
		if(!StringUtil.chekMail(username)) {
			throw new MyIllegalArgumentException("账号不合法，请输入正确的邮箱格式");
		}
		User user = userMapper.getUserByUsername(username);
		if(username == null) {
			throw new IllegalStateException("用户不存在");
		}
		if(!CryptUtil.mathchs(password, user.getPassword())) {
			throw new IllegalStateException("密码错误");
		}
		userAfterHandler(user);
	}
	
	/**
	 * login with username and identifyCode
	 * @param username
	 * @param identifyCode
	 * @throws Exception
	 */
	@LogAnnotation(description="验证码登陆")
	public void loginByIdentifyCode(String username,String identifyCode) throws Exception {
		if(!StringUtil.chekMail(username)) {
			throw new MyIllegalArgumentException("账号不合法，请输入正确的邮箱格式");
		}
		User user = userMapper.getUserByUsername(username);
		if(username == null) {
			throw new IllegalStateException("用户不存在");
		}
		String sessionIc = (String) ThreadLocalHandler.getLocalSession().getAttribute("IDENTIFYCODE_IN_SESSION");
		if(!(StringUtil.hasLength(identifyCode) && identifyCode.equals(sessionIc))) {
			throw new IllegalStateException("验证码错误");
		}
		userAfterHandler(user);
	}

	/**
	 * 注册
	 */
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="注册")
	public void signup(String username, String password, String identifyCode) throws MyIllegalArgumentException,
	IllegalStateException{
		if(!StringUtil.chekMail(username)) {
			throw new MyIllegalArgumentException("账号不合法，请输入正确的邮箱格式");
		}
		if(password.length() < 8 || password.length() > 20 || !StringUtil.checkPassword(password)) {
			throw new MyIllegalArgumentException("密码必须为8~16位，包含字母、数字");
		}
		if(!(StringUtil.hasLength(identifyCode) &&
				identifyCode.equals(ThreadLocalHandler.getLocalSession().getAttribute("IDENTIFYCODE_IN_SESSION")))) {
			throw new MyIllegalArgumentException("验证码错误");
		}
		User user = userMapper.getUserByUsername(username);
		if(user != null) {
			throw new IllegalStateException("用户名已注册");
		}
		user = new User();
		user.setName("游客");
		user.setIcon("/images/upload/man1.png");
		user.setUsername(username);
		user.setPassword(CryptUtil.encryption(password));
		user.setSex(1);
		user.setRole(roleService.queryByName("普通用户"));
		userMapper.saveUser(user);
		ShoppingCar car = new ShoppingCar();
		car.setUser_id(user.getId());
		carService.save(car);
		userAfterHandler(user);
	}
	
	@LogAnnotation(description="用户cookie缓存添加")
	public void userAfterHandler(User user) {
		String token = UUID.randomUUID().toString() + "&";
		token += user.getId();
		Cookie cookie = new Cookie("tk", token);
		cookie.setHttpOnly(true);
		cookie.setMaxAge(7*24*60*60);
		cookie.setPath("/");
		ThreadLocalHandler.getLocalResponse().addCookie(cookie);
		//RedisUtil.set(token, user);
		redisTemplate.opsForValue().set(token, user);
	}
	
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="更新用户信息")
	public User update(User user) throws Exception {
		if(!StringUtil.hasLength(user.getName())) {
			throw new IllegalArgumentException("用户名不能为空");
		}
		if(user.getSex() == null) {
			throw new IllegalArgumentException("性别不能为空");
		}
		HttpServletRequest request = ThreadLocalHandler.getLocalRequest();
		User oldUser = ContextUserUtil.getCurrentUser(request);
		oldUser.setName(user.getName());
		oldUser.setSex(user.getSex());
		oldUser.setNote(user.getNote());
		if(user.getIcon() != null) {
			String oldIcon = oldUser.getIcon();
			File upload = new File(StringUtil.getUploadPath()+oldIcon);
			String newIcon = "/"+user.getIcon();
			if(upload.exists()) {
				upload.delete();
			}
			oldUser.setIcon(newIcon);
		}
		userMapper.updateUser(oldUser);
		User newUser = userMapper.getUser(oldUser.getId());
		updateCache(newUser, request);
		return newUser;
	}
	
	@Transactional(rollbackFor=Exception.class)
	@LogAnnotation(description="修改密码")
	public void updatePassword(String username,String identifyCode,String newPassword) throws Exception {
		if(newPassword.length() < 8 || newPassword.length() > 20 || !StringUtil.checkPassword(newPassword)) {
			throw new MyIllegalArgumentException("密码必须为8~16位，包含字母、数字");
		}
		HttpServletRequest request = ThreadLocalHandler.getLocalRequest();
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		if(user == null) {
			user = userMapper.getUserByUsername(username);
			String code = (String) request.getSession().getAttribute("IDENTIFYCODE_IN_SESSION");
			if(!identifyCode.equals(code)) {
				throw new MyIllegalArgumentException("验证码错误");
			}
		}
		user.setPassword(CryptUtil.encryption(newPassword));
		User newUser = userMapper.getUser(user.getId());
		updateCache(newUser, request);
	}
	
	@LogAnnotation(description="更新缓存中的用户")
	public void updateCache(User user,HttpServletRequest request) {
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("tk".equals(cookie.getName())) {
				String token = cookie.getValue();
				redisTemplate.delete(token);
				redisTemplate.opsForValue().set(token, user);
			}
		}
	}

	@LogAnnotation(description="注销用户")
	public void exit() {
		HttpServletResponse response = ThreadLocalHandler.getLocalResponse();
		HttpServletRequest request = ThreadLocalHandler.getLocalRequest();
		Cookie[] cookies = request.getCookies();
		for (Cookie cookie : cookies) {
			if("tk".equals(cookie.getName())) {
				String token = cookie.getValue();
				User user = (User) redisTemplate.opsForValue().get(token);
				if(user != null) {
					redisTemplate.delete(token);
				}
			}
		}
		Cookie cookie = new Cookie("tk",null);
		cookie.setPath("/");
		cookie.setHttpOnly(true);
		response.addCookie(cookie);
	}

	@LogAnnotation(description="查询用户")
	public PageResult<User> queryUser(UserQuery query) {
		Integer index = (query.getCurrent() - 1) * 11;
		List<User> result = userMapper.queryUser(query,new RowBounds(index,11));
		Integer count = userMapper.getCount(query);
		System.out.println(query.getCurrent());
		return new PageResult<>(result, count, 11,query.getCurrent());
	}

	@LogAnnotation(description="删除用户")
	@Transactional(rollbackFor=Exception.class)
	public void delete(Long id) {
		userMapper.deleteUser(id);
	}
	
}

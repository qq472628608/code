package com.fjw.controller;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.fjw.mail.IdentifyCodeUtil;
import com.fjw.mail.MailUtil;
import com.fjw.service.IUserService;
import com.fjw.util.StringUtil;
import com.fjw.util.ThreadLocalHandler;

import lombok.Setter;

@RestController
public class ApiController {

	@Setter
	@Autowired
	private IUserService userService;

	@RequestMapping(value = "/api/user", method = RequestMethod.POST)
	public void login(String username, String password, HttpServletRequest request, HttpServletResponse response)
			throws Exception {
		ThreadLocalHandler.setAll(request, response);
		userService.login(username, password);
	}

	@RequestMapping(value = "/api/user/identifyCode", method = RequestMethod.GET)
	public void loginByIdentifyCode(String username, String identifyCode, HttpServletRequest request,
			HttpServletResponse response) throws Exception {
		ThreadLocalHandler.setAll(request, response);
		userService.loginByIdentifyCode(username, identifyCode);
	}

	@RequestMapping(value = "/api/identifyCode", method = RequestMethod.POST)
	public void sendIdentifyCode(String username, HttpServletRequest request) {
		String identifyCode = IdentifyCodeUtil.getIdentifyCode();
		MailUtil.sendMail(username, "来自fjw的验证码", "您的验证码为：" + identifyCode + "\r\n10分钟内有效，请尽快输入");
		HttpSession session = request.getSession();
		session.setAttribute("IDENTIFYCODE_IN_SESSION", identifyCode);
		Timer timer = new Timer();
		timer.schedule(new TimerTask() {
			public void run() {
				session.removeAttribute("IDENTIFYCODE_IN_SESSION");
			}
		}, 1000 * 60 * 10);
	}

	@RequestMapping(value = "/api/file",method=RequestMethod.POST)
	public String upload(@RequestParam("file") MultipartFile file, HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		String fileName = file.getOriginalFilename();
		String fileTag = fileName.substring(fileName.lastIndexOf("."),fileName.length());
		String newName = UUID.randomUUID().toString()+fileTag;
		InputStream in;
		try {
			in = file.getInputStream();
			String path = StringUtil.getUploadPath();
			File upload = new File(path);
			if(!upload.exists()) {
				upload.mkdir();
			}
			OutputStream out = new FileOutputStream(path+newName);
			IOUtils.copy(in, out);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return newName;
	}
	
	@RequestMapping(value = "/api/files",method=RequestMethod.POST)
	public List<String> upload(@RequestParam("files")MultipartFile[] files,HttpServletRequest request){
		ThreadLocalHandler.setLocalRequest(request);
		List<String> names = new ArrayList<>();
		for (MultipartFile file : files) {
			String fileName = file.getOriginalFilename();
			String fileTag = fileName.substring(fileName.lastIndexOf("."),fileName.length());
			String newName = UUID.randomUUID().toString()+fileTag;
			InputStream in;
			try {
				in = file.getInputStream();
				String path = StringUtil.getUploadPath();
				File upload = new File(path);
				if(!upload.exists()) {
					upload.mkdir();
				}
				OutputStream out = new FileOutputStream(path+newName);
				IOUtils.copy(in, out);
			} catch (IOException e) {
				e.printStackTrace();
			}
			names.add(newName);
		}
		return names;
	}
	
	@RequestMapping(value="/api/user",method=RequestMethod.DELETE)
	public void exit(HttpServletRequest request,HttpServletResponse response) {
		ThreadLocalHandler.setAll(request, response);
		userService.exit();
	}

}

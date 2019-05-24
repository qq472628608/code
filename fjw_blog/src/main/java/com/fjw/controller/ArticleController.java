package com.fjw.controller;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.fjw.service.IArticleService;

import lombok.Setter;

@Controller
public class ArticleController {
	@Setter@Autowired
	private IArticleService articleService;
	
	@RequestMapping(value="/getArticles",produces="text/html;charset=utf-8;")
	@ResponseBody
	public String getArticles(HttpServletRequest request) {
		return articleService.getArticles(request);
	}
	
	@RequestMapping(value="/searchArticles",produces="text/html;charset=utf-8;")
	@ResponseBody
	public String searchArticles(String keywords,HttpServletRequest request) {
		return articleService.searchArticle(keywords,request);
	}
	
	@RequestMapping(value="/getArticle",produces="text/html;charset=utf-8;")
	@ResponseBody
	public String getArticle(Long id,HttpServletRequest request) {
		Long sArticleid = (Long) request.getSession().getAttribute("UPDATE_ARTICLEID_IN_SESSION");
		if(id != null) {
			return articleService.getArticle(id,request);
		}
		if(sArticleid != null&&id == null) {
			return articleService.getArticle(sArticleid, request);
		}
		return null;
	}
	
	@RequestMapping(value="/getUserArticles",produces="text/html;charset=utf-8;")
	@ResponseBody
	public String getUserArticles(Long id) {
		return articleService.getUserArticles(id);
	}
	
	@RequestMapping("/collect")
	public void collect(Long userid,Long articleid,HttpServletRequest request,HttpServletResponse response) {
		articleService.collect(userid, articleid,request);
	}
	
	@RequestMapping("/removeCollection")
	public void removeCollection(Long userid,Long articleid,HttpServletRequest request,HttpServletResponse response) {
		articleService.removeCollection(userid, articleid,request);
	}
	
	@RequestMapping("/love")
	@ResponseBody
	public String love(Boolean isAdd,Long id) {
		return articleService.love(isAdd, id);
	}
	
	@RequestMapping(value="/getFourComment",produces="text/html;charset=utf-8;")
	@ResponseBody
	public String getFourComment() {
		return articleService.getFourComment();
	}
	
	@RequestMapping(value="/getArticlesByKind",produces="text/html;charset=utf-8;")
	@ResponseBody
	public String getArticlesByKind(Integer kindSn) {
		return articleService.getArticlesByKind(kindSn);
	}
	
	@RequestMapping("/saveArticle")
	public String saveArticle(@RequestParam("file")MultipartFile file,String title,Integer kindSn,String summary,
			String text,Long articleid,HttpServletRequest request) {
		String oldName = file.getOriginalFilename();
		String name = UUID.randomUUID().toString();
		String suffix = oldName.substring(oldName.lastIndexOf("."));
		String fileName = name+suffix;
		try {
			InputStream in = file.getInputStream();
			String path = request.getServletContext().getRealPath("/upload/"+fileName);
			OutputStream out = new FileOutputStream(path);
			IOUtils.copy(in, out);
			articleService.saveArticle(title, kindSn, summary, text, fileName,articleid);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return "index";
	}
	
	@RequestMapping("/fjwblog_edit")
	public String editArticle(String username,String password) {
		if("472628608@qq.com".equals(username)&&"rabbit2533".equals(password)) 
			return "forward:/WEB-INF/edit.html";
		return "index";
	}
	
	@RequestMapping("/updateArticle")
	public String updateArticle(Long articleid,HttpServletRequest request) {
		request.getSession().setAttribute("UPDATE_ARTICLEID_IN_SESSION", articleid);
		return "forward:/WEB-INF/edit.html";
	}
}

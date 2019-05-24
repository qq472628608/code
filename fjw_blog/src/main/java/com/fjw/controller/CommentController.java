package com.fjw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fjw.service.ICommentService;

import lombok.Setter;

@Controller
public class CommentController {
	@Setter@Autowired
	private ICommentService commentService;
	
	@RequestMapping(value="/listComment",produces="text/html;charset=utf-8;")
	@ResponseBody
	public String listComment(Long articleid) {
		return commentService.listComment(articleid); 
	}
	
	@RequestMapping("/saveComment")
	public void saveComment(String text,Long articleid,Long parentid,HttpServletRequest request,HttpServletResponse response) {
		commentService.saveComment(text, articleid, parentid,request);
	}
}

package com.fjw.service.impl;


import java.util.Date;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fjw.domain.Article;
import com.fjw.domain.Comment;
import com.fjw.domain.User;
import com.fjw.log.LogAnnotation;
import com.fjw.mapper.ArticleMapper;
import com.fjw.mapper.CommentMapper;
import com.fjw.service.ICommentService;

import lombok.Setter;

@Service
public class CommentServiceImpl implements ICommentService{
	@Setter@Autowired
	private CommentMapper commentDAO;
	@Setter@Autowired
	private ArticleMapper articleDAO;
	
	@LogAnnotation(description="获取文章所有评论")
	public String listComment(Long articleid) {
		return JSON.toJSONString(commentDAO.list(articleid),SerializerFeature.DisableCircularReferenceDetect);
	}
	
	@LogAnnotation(description="添加一条评论")
	public void saveComment(String text,Long articleid,Long parentid,HttpServletRequest request) {
		if(request != null) {
			User user = (User) request.getSession().getAttribute("USER_IN_SESSION");
			Article article = articleDAO.get(articleid);
			Comment comment = new Comment();
			comment.setText(text);
			comment.setTime(new Date());
			comment.setUser(user);
			comment.setArticle(article);
			if(parentid != null) {
				comment.setParent(commentDAO.get(parentid));
			}
			commentDAO.save(comment);
		}
	}

}

package com.fjw.service;

import javax.servlet.http.HttpServletRequest;

public interface ICommentService {
	public String listComment(Long articleid);
	public void saveComment(String text,Long articleid,Long parentid,HttpServletRequest request);
}

package com.fjw.service;

import javax.servlet.http.HttpServletRequest;

public interface IArticleService {
	public String getArticles(HttpServletRequest request);
	public String searchArticle(String keywords,HttpServletRequest request);
	public String getArticle(Long id,HttpServletRequest request);
	public String getUserArticles(Long id);
	public void collect(Long userid,Long articleid,HttpServletRequest request);
	public void removeCollection(Long userid,Long articleid,HttpServletRequest request);
	public String love(Boolean isAdd,Long id);
	public String getFourComment();
	public String getArticlesByKind(Integer kind);
	public void saveArticle(String title,Integer kind,String summary,String text,String image,Long articleid);
}

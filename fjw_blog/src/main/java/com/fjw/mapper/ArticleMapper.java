package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.fjw.domain.Article;

public interface ArticleMapper {
	public void save(Article article);
	public void delete(Long id);
	public Article get(Long id);
	public void update(Article article);
	public List<Article> list();
	public List<Article> query(String keywords);
	public List<Article> selectByUserid(Long id);
	public void saveUserAndArticle(@Param("userid")Long userid,@Param("articleid")Long articleid);
	public void deleteUserAndArticle(@Param("userid")Long userid,@Param("articleid")Long articleid);
}

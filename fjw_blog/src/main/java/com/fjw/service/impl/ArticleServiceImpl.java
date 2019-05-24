package com.fjw.service.impl;


import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.serializer.SerializerFeature;
import com.fjw.domain.Article;
import com.fjw.domain.Comment;
import com.fjw.domain.Kind;
import com.fjw.domain.User;
import com.fjw.log.LogAnnotation;
import com.fjw.mapper.ArticleMapper;
import com.fjw.mapper.CommentMapper;
import com.fjw.mapper.KindMapper;
import com.fjw.service.IArticleService;
import com.fjw.util.StringUtil;

import lombok.Setter;

@Service
public class ArticleServiceImpl implements IArticleService{
	@Setter@Autowired
	private ArticleMapper articleDAO;
	@Setter@Autowired
	private CommentMapper commentDAO;
	@Setter@Autowired
	private KindMapper kindDAO;

	//return all articles
	@LogAnnotation(description="获取所有文章")
	public String getArticles(HttpServletRequest request) {
		List<Article> articles = articleDAO.list();
		User user = (User) request.getSession().getAttribute("USER_IN_SESSION");
		if(user != null) {
			List<Article> collection = user.getList();
			for (Article article : articles) {
				if(collection.contains(article)) {
					article.setTag1("fa fa-star");
				}
			}
		}
		return JSON.toJSONString(articles,SerializerFeature.DisableCircularReferenceDetect);
		
	}
	
	//return search articles
	@LogAnnotation(description="关键字查询文章")
	public String searchArticle(String keywords,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("USER_IN_SESSION");
		if(StringUtil.hasLength(keywords)) {
			List<Article> articles = articleDAO.query("%"+keywords+"%");
			if(user != null) {
				List<Article> collection = user.getList();
				for (Article article : articles) {
					if(collection.contains(article)) {
						article.setTag1("fa fa-star");
					}
				}
			}
			return JSON.toJSONString(articles,SerializerFeature.DisableCircularReferenceDetect);
		}else {
			List<Article> all = articleDAO.list();
			if(user != null) {
				List<Article> collection = user.getList();
				for (Article article : all) {
					if(collection.contains(article)) {
						article.setTag1("fa fa-star");
					}
				}
			}
			return JSON.toJSONString(all,SerializerFeature.DisableCircularReferenceDetect);
		}	
	}
	
	//get article by id
	@LogAnnotation(description="根据传入的id获取文章")
	public String getArticle(Long id,HttpServletRequest request) {
		Article article = articleDAO.get(id);
		User user = (User) request.getSession().getAttribute("USER_IN_SESSION");
		if(user != null) {
			List<Article> collection = user.getList();
			if(collection.contains(article)) {
				article.setTag1("fa fa-star");
			}
		}
		return JSON.toJSONString(article);
	}
	
	//get users articles
	@LogAnnotation(description="获取当前用户收藏的文章")
	public String getUserArticles(Long id) {
		List<Article> articles = articleDAO.selectByUserid(id);
		for (Article article : articles) {
			article.setTag1("fa fa-star");
		}
		return JSON.toJSONString(articles,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	//collect article
	@LogAnnotation(description="收藏一篇文章")
	public void collect(Long userid, Long articleid,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("USER_IN_SESSION");
		if(user != null) {
			articleDAO.saveUserAndArticle(userid, articleid);
			Article article = articleDAO.get(articleid);
			user.getList().add(article);
		}
	}
	
	//remove collection
	@LogAnnotation(description="从收藏中移除一篇文章")
	public void removeCollection(Long userid,Long articleid,HttpServletRequest request) {
		User user = (User) request.getSession().getAttribute("USER_IN_SESSION");
		if(user != null) {
			articleDAO.deleteUserAndArticle(userid, articleid);
			Article article = articleDAO.get(articleid);
			user.getList().remove(article);
		}
	}
	//change love
	@LogAnnotation(description="点赞")
	public String love(Boolean isAdd,Long id) {
		Article article = articleDAO.get(id);
		if(article != null) {
			Integer loveNum = article.getLove();
			if(isAdd) {
				article.setLove(loveNum+1);
				articleDAO.update(article);
				return (loveNum+1)+"";
			}else {
				article.setLove(loveNum-1);
				articleDAO.update(article);
				return (loveNum-1)+"";
			}
		}
		return null;
	}
	
	//get four newest comment
	@LogAnnotation(description="获取最新四条评论")
	public String getFourComment() {
		List<Comment> list = commentDAO.listFour();
		return JSON.toJSONString(list,SerializerFeature.DisableCircularReferenceDetect);
	}

	//get article by kind
	@LogAnnotation(description="根据文章的类别查询文章")
	public String getArticlesByKind(Integer kind) {
		List<Article> all = articleDAO.list();
		List<Article> articles = new ArrayList<>();
		for (Article article : all) {
			if(article.getKind().getSn() == kind) {
				articles.add(article);
			}
		}
		return JSON.toJSONString(articles,SerializerFeature.DisableCircularReferenceDetect);
	}
	
	//add article
	@LogAnnotation(description="保存一篇文章")
	public void saveArticle(String title, Integer kindSn, String summary, String text, String image, Long articleid) {
		Article article = null;
		if(articleid != null) {
			article = articleDAO.get(articleid);
			String path = article.getImage();
			File file = new File(path);
			if(file.exists()) {
				file.delete();
			}
		}else {
			article = new Article();
			article.setLove(0);
		}
		article.setTitle(title);
		Kind kind = kindDAO.get(kindSn);
		article.setSummary(summary);
		article.setText(text);
		article.setImage("./upload/"+image);
		article.setKind(kind);
		if(articleid != null) {
			articleDAO.update(article);
		}else {
			articleDAO.save(article);
		}
	}	
}

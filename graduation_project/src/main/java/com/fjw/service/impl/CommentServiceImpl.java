package com.fjw.service.impl;

import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.Comment;
import com.fjw.domain.CommentImg;
import com.fjw.domain.User;
import com.fjw.dto.CommentDto;
import com.fjw.mapper.CommentImgMapper;
import com.fjw.mapper.CommentMapper;
import com.fjw.service.ICommentService;
import com.fjw.util.ContextUserUtil;
import com.fjw.util.ThreadLocalHandler;
import com.fjw.vo.StarVo;

import lombok.Setter;

@Service
public class CommentServiceImpl implements ICommentService {
	
	@Setter@Autowired@Lazy
	private CommentMapper commentMapper;
	@Setter@Autowired@Lazy
	private CommentImgMapper commentImgMapper;

	@CacheEvict(value="comment",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="添加评论")
	public List<Comment> save(CommentDto comment) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		comment.setUser_id(user.getId());
		Integer fourStar = comment.getGeneral() + comment.getSellerService()
		+ comment.getFit() + comment.getSellerSpeed();
		comment.setStar((Integer)fourStar / 4);
		comment.setTime(new Date());
		commentMapper.saveComment(comment);
		List<String> urls = comment.getUrls();
		if(urls != null) {
			for (String url : urls) {
				CommentImg img = new CommentImg();
				img.setComment_id(comment.getId());
				img.setUrl("/"+url);
				commentImgMapper.saveCommentImg(img);
			}
		}
		return list(comment.getKa_id(),1);
	}
	
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="删除评论")
	public void delete(Long id) {
		commentMapper.deleteComment(id);
	}

	@Cacheable(value="comment")
	@LogAnnotation(description="查询当前商品评论")
	public List<Comment> list(Long ka_id, Integer current) {
		RowBounds row = new RowBounds((current - 1)*10,10);
		return commentMapper.listByProduct(ka_id, row);
	}
	
	@LogAnnotation(description="查询当前商品评分")
	public StarVo getStar(Long ka_id) {
		List<Comment> comments = commentMapper.listAll(ka_id);
		if(comments.size() > 0) {
			Integer size = comments.size();
			Float total = 0F;
			Float general = 0F;
			Float fit = 0F;
			Float sellerService = 0F;
			Float sellerSpeed = 0F;
			for (Comment comment : comments) {
				total += comment.getStar();
				general += comment.getGeneral();
				fit += comment.getFit();
				sellerService += comment.getSellerService();
				sellerSpeed += comment.getSellerSpeed();
			}
			return new StarVo(size,total/size, general/size, fit/size, sellerService/size, sellerSpeed/size);
		}
		return new StarVo(1,5F,5F,5F,5F,5F);
	}

}

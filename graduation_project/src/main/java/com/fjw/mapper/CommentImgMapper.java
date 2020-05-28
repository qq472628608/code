package com.fjw.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.fjw.domain.CommentImg;

@Mapper
public interface CommentImgMapper {
	public void saveCommentImg(CommentImg commentImg);
	public void list(Long comment_id);
	public void delete(Long comment_id);
}

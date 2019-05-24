package com.fjw.mapper;

import java.util.List;

import com.fjw.domain.Comment;

public interface CommentMapper {
	public void save(Comment comment);
	public void delete(Long id);
	public Comment get(Long id);
	public void update(Comment comment);
	public List<Comment> listFour();
	public List<Comment> list(Long id);
}

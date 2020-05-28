package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.fjw.domain.Comment;
import com.fjw.dto.CommentDto;

@Mapper
public interface CommentMapper {
	public void saveComment(CommentDto comment);
	public void deleteComment(Long id);
	public Comment getComment(Long id);
	public List<Comment> list(RowBounds rowBounds);
	public List<Comment> listByProduct(Long ka_id,RowBounds rowBounds);
	public void batchDelete(Long[] ids);
	public List<Comment> listAll(Long ka_id);
}

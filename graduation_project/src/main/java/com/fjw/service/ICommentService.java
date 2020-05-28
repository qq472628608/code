package com.fjw.service;


import java.util.List;

import com.fjw.domain.Comment;
import com.fjw.dto.CommentDto;
import com.fjw.vo.StarVo;

public interface ICommentService {
	public List<Comment> save(CommentDto comment);
	public void delete(Long id);
	public List<Comment> list(Long ka_id,Integer current);
	public StarVo getStar(Long ka_id);
}

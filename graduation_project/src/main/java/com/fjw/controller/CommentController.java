package com.fjw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.Comment;
import com.fjw.dto.CommentDto;
import com.fjw.service.ICommentService;
import com.fjw.util.ThreadLocalHandler;
import com.fjw.vo.CommentVo;
import com.fjw.vo.StarVo;

import lombok.Setter;

@RestController
public class CommentController {

	@Setter@Autowired
	private ICommentService commentService;
	
	@RequestMapping(value="/comment",method=RequestMethod.POST)
	public List<CommentVo> save(@Valid CommentDto comment,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		List<CommentVo> vos = new ArrayList<>();
		for (Comment c : commentService.save(comment)) {
			vos.add(new CommentVo(c));
		}
		return vos;
	}
	
	@RequestMapping(value="/comments",method=RequestMethod.GET)
	public List<CommentVo> list(Long ka_id,Integer current){
		List<CommentVo> vos = new ArrayList<>();
		for (Comment c : commentService.list(ka_id, current)) {
			vos.add(new CommentVo(c));
		}
		return vos;
	}
	
	@RequestMapping(value="/comments/star/{ka_id}",method=RequestMethod.GET)
	public StarVo getStar(@PathVariable("ka_id")Long ka_id) {
		StarVo star = commentService.getStar(ka_id);
		return star;
	}
}

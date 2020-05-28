package com.fjw.vo;

import java.io.Serializable;
import java.util.List;

import com.fjw.domain.Comment;
import com.fjw.domain.CommentImg;
import com.fjw.util.StringUtil;

import lombok.Data;

@Data
public class CommentVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String text;
	private Integer star;
	private String time;
	private String userName;
	private String icon;
	private List<CommentImg> imgs;
	
	
	public CommentVo(Comment comment) {
		this.id = comment.getId();
		this.text = comment.getText();
		this.star = comment.getStar();
		this.time = StringUtil.getFormatDate(comment.getTime());
		this.userName = comment.getUser().getName();
		this.icon = comment.getUser().getIcon();
		this.imgs = comment.getImgs();
	}
}

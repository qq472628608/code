package com.fjw.domain;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("Comment")
@JsonIgnoreProperties(value = {"handler"})
public class Comment implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String text;
	private Integer star;
	private Integer general;
	private Integer sellerService;
	private Integer fit;
	private Integer sellerSpeed;
	private Date time;
	private User user;
	private KeyAttribute keyAttribute;
	private List<CommentImg> imgs;
}

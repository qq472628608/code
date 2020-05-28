package com.fjw.dto;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.validation.constraints.NotNull;

import org.apache.ibatis.type.Alias;
import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;
@Data@Alias("CommentDto")
@JsonIgnoreProperties(value = {"handler"})
public class CommentDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String text;
	private Integer star;
	@Range(min=1,max=5,message="评分不合法")
	private Integer general;
	@Range(min=1,max=5,message="评分不合法")
	private Integer sellerService;
	@Range(min=1,max=5,message="评分不合法")
	private Integer fit;
	@Range(min=1,max=5,message="评分不合法")
	private Integer sellerSpeed;
	private Date time;
	private Long user_id;
	@NotNull(message="评论不合法")
	private Long ka_id;
	private List<String> urls;
}

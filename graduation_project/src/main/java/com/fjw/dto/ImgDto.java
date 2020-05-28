package com.fjw.dto;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class ImgDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long state;
	private Long color_id;
	private Long key_id;
	private List<String> urls;
}

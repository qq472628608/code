package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("Color")
@JsonIgnoreProperties(value = {"handler"})
public class Color implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String url;
}

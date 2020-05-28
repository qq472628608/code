package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("Area")
@JsonIgnoreProperties(value = {"handler"})
public class Area implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Integer level;
	private Area parent;
}

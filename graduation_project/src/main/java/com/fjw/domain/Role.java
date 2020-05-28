package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter
@Getter
@ToString
@Alias("Role")
@JsonIgnoreProperties(value = {"handler"})
public class Role implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private Integer sn;
}

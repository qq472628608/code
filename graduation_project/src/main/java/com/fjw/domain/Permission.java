package com.fjw.domain;

import java.io.Serializable;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("Permission")
@JsonIgnoreProperties(value = {"handler"})
public class Permission implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String sn;
	private List<Role> roles; 
}

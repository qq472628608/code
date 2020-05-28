package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Setter@Getter@ToString@Alias("User")
@JsonIgnoreProperties(value = {"handler"})
public class User implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String username;
	private String password;
	private String name;
	private Integer sex;
	private String phone;
	private String note;
	private String icon;
	private Role role;
}

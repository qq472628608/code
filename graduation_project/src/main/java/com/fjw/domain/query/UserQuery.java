package com.fjw.domain.query;

import java.io.Serializable;


import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("UserQuery")
@JsonIgnoreProperties(value = {"handler"})
public class UserQuery implements Serializable {
	private static final long serialVersionUID = 1L;
	private String username;
	private Integer sex;
	private String phone;
	private String roleName;
	private Integer current;
}

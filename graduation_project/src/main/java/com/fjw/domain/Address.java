package com.fjw.domain;

import java.io.Serializable;


import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("Address")
@JsonIgnoreProperties(value = {"handler"})
public class Address implements Serializable {
	
	private static final long serialVersionUID = 1L;
	
	private Long id;
	private String name;
	private String phone;
	private Area province;
	private Area city;
	private Area county;
	private String detail;
	private Integer isDefault;
	private Long user_id;
}

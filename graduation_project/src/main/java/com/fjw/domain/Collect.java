package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("Collect")
@JsonIgnoreProperties(value = {"handler"})
public class Collect implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Product product;
	private Long user_id;
}

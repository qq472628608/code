package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("ShoppingcarProduct")
@JsonIgnoreProperties(value = {"handler"})
public class ShoppingcarProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long car_id;
	private Product product;
	private Integer buyNumber;
}

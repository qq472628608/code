package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("OrderProduct")
@JsonIgnoreProperties(value = {"handler"})
public class OrderProduct implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private Long order_id;
	private Product product;
	private Integer buyNumber;
}

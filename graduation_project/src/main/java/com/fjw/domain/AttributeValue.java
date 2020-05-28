package com.fjw.domain;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("AttributeValue")
@JsonIgnoreProperties(value = {"handler"})
public class AttributeValue implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String value;
	private SaleAttribute saleAttribute;
}

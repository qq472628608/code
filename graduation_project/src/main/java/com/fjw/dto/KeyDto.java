package com.fjw.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class KeyDto implements Serializable{
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String introduction;
	private BigDecimal basePrice;
	private String simpleIntroduction;
	private Long kind_id;
}

package com.fjw.domain;

import java.io.Serializable;
import java.math.BigDecimal;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("KeyAttribute")
@JsonIgnoreProperties(value = {"handler"})
public class KeyAttribute implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String name;
	private String introduction;
	private BigDecimal basePrice;
	private String simpleIntroduction;
	private ProductKind kind;
	private GeneralProductImg img;
}

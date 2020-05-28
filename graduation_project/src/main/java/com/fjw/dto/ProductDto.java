package com.fjw.dto;

import java.io.Serializable;

import org.hibernate.validator.constraints.Range;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class ProductDto implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long product_id;
	@Range(min=0,max=999999,message="购买数量不合法(0~999999)")
	private Integer number;
}

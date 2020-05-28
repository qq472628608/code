package com.fjw.domain.query;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.validation.constraints.Min;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data@Alias("ProductQuery")
public class ProductQuery implements Serializable {
	private static final long serialVersionUID = 1L;
	private String name;
	@Min(value = 0,message="价格不合法")
	private BigDecimal min;
	@Min(value = 0,message="价格不合法")
	private BigDecimal max;
	private Integer state;
	private Integer current;
}

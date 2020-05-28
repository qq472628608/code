package com.fjw.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.RoundingMode;

import lombok.Data;

@Data
public class PriceVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private BigDecimal productsPrice;
	private BigDecimal postPrice;
	private BigDecimal totalPrice;
	
	public PriceVo(BigDecimal productsPrice,BigDecimal postPrice) {
		this.productsPrice = productsPrice.setScale(2, RoundingMode.HALF_UP);
		this.postPrice = postPrice.setScale(2,RoundingMode.HALF_UP);
		this.totalPrice = productsPrice.add(postPrice).setScale(2, RoundingMode.HALF_UP);
	}
}

package com.fjw.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class StockOutcomeBillItem {
	private Long id;
	private BigDecimal salePrice;
	private BigDecimal number;
	private BigDecimal amount;
	private String remark;
	private Product product;
	private StockOutcomeBill bill;
}

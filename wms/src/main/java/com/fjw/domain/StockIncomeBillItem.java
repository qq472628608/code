package com.fjw.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class StockIncomeBillItem {
	private Long id;
	private BigDecimal costPrice;
	private BigDecimal number;
	private BigDecimal amount;
	private String remark;
	private Product product;
	private StockIncomeBill bill;
}

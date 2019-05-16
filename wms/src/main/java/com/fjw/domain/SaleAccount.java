package com.fjw.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SaleAccount {
	private Long id;
	private Date vdate;
	private BigDecimal number;
	private BigDecimal costPrice;
	private BigDecimal costAmount;
	private BigDecimal salePrice;
	private BigDecimal saleAmount;
	private Product product;
	private Employee saleMan;
	private Client client;
}

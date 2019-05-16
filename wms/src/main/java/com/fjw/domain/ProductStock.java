package com.fjw.domain;

import java.math.BigDecimal;
import java.util.Date;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ProductStock {
	private Long id;
	private BigDecimal price;
	private BigDecimal storeNumber;
	private BigDecimal amount;
	private Date incomeDate;
	private Date outcomeDate;
	private Product product;
	private Depot depot;
}

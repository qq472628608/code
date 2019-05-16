package com.fjw.domain;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderBillItem {
	private Long id;
	private BigDecimal costPrice;
	private BigDecimal number;
	private BigDecimal amount;
	private String remark;
	private Product product;
	private OrderBill bill;
	@Override
	public String toString() {
		return "OrderBillItem [id=" + id + ", costPrice=" + costPrice + ", number=" + number + ", amount=" + amount
				+ ", remark=" + remark + ", product=" + product + ", bill=" + bill + "]";
	}
	
}

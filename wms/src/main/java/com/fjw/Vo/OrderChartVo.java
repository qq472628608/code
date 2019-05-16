package com.fjw.Vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class OrderChartVo {
	private String group;
	private BigDecimal totalNumber;
	private BigDecimal amount;
	
	public OrderChartVo(String group, BigDecimal totalNumber, BigDecimal amount) {
		this.group = group;
		this.totalNumber = totalNumber;
		this.amount = amount;
	}

	public String toString() {
		return "OrderChartVo [group=" + group + ", totalNumebr=" + totalNumber + ", amount=" + amount + "]";
	}
	
}

package com.fjw.Vo;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SaleChartVo {
	private String group;
	private BigDecimal totalNumber;
	private BigDecimal amount;
	private BigDecimal profit;
	
	public SaleChartVo(String group, BigDecimal totalNumber, BigDecimal amount, BigDecimal profit) {
		this.group = group;
		this.totalNumber = totalNumber;
		this.amount = amount;
		this.profit = profit;
	}

	public String toString() {
		return "SaleChartVo [group=" + group + ", totalNumber=" + totalNumber + ", amount=" + amount + ", profit="
				+ profit + "]";
	}
	
}

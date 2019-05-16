package com.fjw.Vo;

import java.math.BigDecimal;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class ChartJson {
	private List<String> groupNames;
	private List<BigDecimal> amounts;
	
	public ChartJson(List<String> groupNames,List<BigDecimal> amounts) {
		this.groupNames = groupNames;
		this.amounts = amounts;
	}
}

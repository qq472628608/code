package com.fjw.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class StockIncomeBill {
	private Long id;
	private String sn;
	private Date vdate;
	private Integer status;
	private BigDecimal totalAmount;
	private BigDecimal totalNumber;
	private Date auditTime;
	private Date inputTime;
	private Employee inputUser;
	private Employee auditor;
	private Supplier supplier;
	private Depot depot;
	
	private List<StockIncomeBillItem> items = new ArrayList<>();
}

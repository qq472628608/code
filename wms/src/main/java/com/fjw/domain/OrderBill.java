package com.fjw.domain;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class OrderBill {
	private Long id;
	private String sn;//订单代号
	private Date vdate;//业务时间
	private Integer status;//审批状态
	private BigDecimal totalAmount;//商品总数
	private BigDecimal totalNumber;//商品总价
	private Date auditTime;//审批时间
	private Date inputTime;//提交时间
	private Employee inputUser;//提交人
	private Employee auditor;//审批人
	private Supplier supplier;//供应商
	
	private List<OrderBillItem> items = new ArrayList<>();//订单明细
}

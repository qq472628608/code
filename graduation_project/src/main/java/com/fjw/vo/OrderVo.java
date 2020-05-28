package com.fjw.vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fjw.domain.OrderList;
import com.fjw.domain.OrderProduct;
import com.fjw.util.StringUtil;

import lombok.Data;

@Data
@JsonIgnoreProperties(value = {"handler"})
public class OrderVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String orderCode;
	private String state;
	private String mark;
	private BigDecimal postPrice;
	private String createTime;
	private Long address_id;
	private List<OrderProduct> products;
	
	public OrderVo(OrderList order) {
		this.id = order.getId();
		this.orderCode = order.getOrderCode();
		switch(order.getOrderState()) {
		case 1:this.state = "待付款";break;
		case 2:this.state = "待发货";break;
		case 3:this.state = "已发货";break;
		case 4:this.state = "其他";break;
		}
		this.mark = order.getMark();
		this.postPrice = order.getPostPrice();
		this.createTime = StringUtil.getFormatDate(order.getCreateTime());
		this.address_id = order.getAddress() != null?order.getAddress().getId():null;
		this.products = order.getProducts();
	}
}

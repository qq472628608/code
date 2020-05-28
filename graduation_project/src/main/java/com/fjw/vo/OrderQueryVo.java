package com.fjw.vo;

import java.io.Serializable;

import com.fjw.domain.Address;
import com.fjw.domain.OrderList;
import com.fjw.domain.User;
import com.fjw.util.StringUtil;

import lombok.Data;

@Data
public class OrderQueryVo implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderCode;
	private String orderState;
	private String createTime;
	private User user;
	private Address address;
	
	public OrderQueryVo(OrderList order) {
		this.orderCode = order.getOrderCode();
		switch(order.getOrderState()) {
		case 1:this.orderState = "待付款";break;
		case 2:this.orderState = "待发货";break;
		case 3:this.orderState = "已发货";break;
		case 4:this.orderState = "其他";break;
		}
		this.createTime = StringUtil.getFormatDate(order.getCreateTime());
		this.user = order.getUser();
		this.address = order.getAddress();
	}
}

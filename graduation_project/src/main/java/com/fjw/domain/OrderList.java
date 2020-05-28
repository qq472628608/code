package com.fjw.domain;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.type.Alias;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.Data;

@Data@Alias("OrderList")
@JsonIgnoreProperties(value = {"handler"})
public class OrderList implements Serializable {
	private static final long serialVersionUID = 1L;
	private Long id;
	private String orderCode;
	private Integer orderState;
	private String mark;
	private BigDecimal postPrice;
	private Date createTime;
	private User user;
	private Address address;
	private List<OrderProduct> products; 
}

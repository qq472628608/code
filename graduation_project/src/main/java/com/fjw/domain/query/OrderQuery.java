package com.fjw.domain.query;

import java.io.Serializable;

import org.apache.ibatis.type.Alias;

import lombok.Data;

@Data@Alias("OrderQuery")
public class OrderQuery implements Serializable {
	private static final long serialVersionUID = 1L;
	private String orderCode;
	private Integer orderState;
	private String username;
	private String phone;
	private Integer current;
}

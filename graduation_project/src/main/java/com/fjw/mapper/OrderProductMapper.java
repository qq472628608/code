package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fjw.domain.OrderProduct;

@Mapper
public interface OrderProductMapper {
	public void saveOrderProduct(OrderProduct orderProduct);
	public void deleteOrderProduct(Long id);
	public OrderProduct getOrderProduct(Long id);
	public void updateOrderProduct(OrderProduct orderProduct);
	public List<OrderProduct> listProduct(Long order_id);
}

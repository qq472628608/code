package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.session.RowBounds;

import com.fjw.domain.OrderList;
import com.fjw.domain.query.OrderQuery;

@Mapper
public interface OrderListMapper {
	public void saveOrderList(OrderList order);
	public void deleteOrderList(@Param("id")Long id,@Param("user_id")Long user_id);
	public OrderList getOrderList(Long id);
	public void updateOrderList(@Param("orderCode")String orderCode,@Param("orderState")Integer orderState,
			@Param("mark")String mark,@Param("address_id")Long address_id);
	public List<OrderList> list(RowBounds rowBounds);
	public List<OrderList> listByUser(Long user_id);
	public List<OrderList> query(OrderQuery orderQuery,RowBounds rowBounds);
	public Integer getCount(OrderQuery orderQuery);
	public void batchDelete(Long[] ids);
	public OrderList getByOrderCode(@Param("orderCode")String orderCode,@Param("user_id")Long user_id);
	public List<OrderList> listByState(@Param("state")Integer state,@Param("user_id")Long user_id);
	public void finishOrderList(String orderCode);
}

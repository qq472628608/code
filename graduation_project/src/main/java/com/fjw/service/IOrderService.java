package com.fjw.service;

import java.util.List;

import com.fjw.domain.OrderList;
import com.fjw.domain.query.OrderQuery;
import com.fjw.domain.query.PageResult;
import com.fjw.dto.ProductDto;
import com.fjw.vo.OrderQueryVo;
import com.fjw.vo.PriceVo;

public interface IOrderService {
	
	public String save(List<ProductDto> ops);
	
	public List<OrderList> delete(Long id);
	
	public OrderList get(Long id);
	
	public void update(String orderCode,Integer orderState,String mark,Long address_id);
	
	public List<OrderList> listByUser();
	
	public OrderList getByOrderCode(String orderCode);
	
	public OrderList getRecent();
	
	public List<OrderList> listByState(Integer state);
	
	public PriceVo getTotalPrice(String orderCode);
	
	public PageResult<OrderQueryVo> query(OrderQuery query);
	
	public void finishOrder(String orderCode);
	
}

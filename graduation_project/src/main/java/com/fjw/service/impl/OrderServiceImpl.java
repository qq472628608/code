package com.fjw.service.impl;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.OrderList;
import com.fjw.domain.OrderProduct;
import com.fjw.domain.Product;
import com.fjw.domain.User;
import com.fjw.domain.query.OrderQuery;
import com.fjw.domain.query.PageResult;
import com.fjw.dto.ProductDto;
import com.fjw.mapper.AddressMapper;
import com.fjw.mapper.OrderListMapper;
import com.fjw.mapper.OrderProductMapper;
import com.fjw.mapper.ProductMapper;
import com.fjw.service.IOrderService;
import com.fjw.util.ContextUserUtil;
import com.fjw.util.StringUtil;
import com.fjw.util.ThreadLocalHandler;
import com.fjw.vo.OrderQueryVo;
import com.fjw.vo.PriceVo;

import lombok.Setter;

@Service
public class OrderServiceImpl implements IOrderService {
	
	@Setter@Autowired@Lazy
	private OrderListMapper orderMapper;
	@Setter@Autowired@Lazy
	private OrderProductMapper opMapper;
	@Setter@Autowired@Lazy
	private ProductMapper productMapper;
	@Setter@Autowired@Lazy
	private AddressMapper addressMapper;

	@CacheEvict(value="order",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="添加订单")
	public String save(List<ProductDto> ops) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		OrderProduct orderProduct = new OrderProduct();
		OrderList order = new OrderList();
		order.setAddress(null);
		order.setPostPrice(new BigDecimal(0.00));
		order.setOrderState(1);
		order.setMark(null);
		order.setUser(user);
		order.setCreateTime(new Date());
		order.setAddress(addressMapper.getDefaultAddress(user.getId()));
		String orderCode = StringUtil.getOrderCode()+user.getId();
		if(orderCode.length() > 20) {
			orderCode = orderCode.substring(0, 20);
		}
		order.setOrderCode(orderCode);
		orderMapper.saveOrderList(order);
		for (ProductDto op : ops) {
			orderProduct.setProduct(productMapper.getProduct(op.getProduct_id()));
			orderProduct.setBuyNumber(op.getNumber());
			orderProduct.setOrder_id(order.getId());
			opMapper.saveOrderProduct(orderProduct);
		}
		return orderCode;
	}

	@CacheEvict(value="order",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="删除订单")
	public List<OrderList> delete(Long id) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		orderMapper.deleteOrderList(id,user.getId());
		return orderMapper.listByUser(user.getId());
	}

	@Cacheable(value="order")
	@LogAnnotation(description="根据id查询一个订单")
	public OrderList get(Long id) {
		return orderMapper.getOrderList(id);
	}

	@Cacheable(value="order")
	@LogAnnotation(description="根据订单号查询一个订单")
	public OrderList getByOrderCode(String orderCode) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		return orderMapper.getByOrderCode(orderCode,user.getId());
	}
	
	@CacheEvict(value="order",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="更新订单信息")
	public void update(String orderCode,Integer orderState,String mark,Long address_id) {
		orderMapper.updateOrderList(orderCode, orderState, mark, address_id);
	}
	
	@CacheEvict(value="order",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="完成订单（试用）")
	public void finishOrder(String orderCode) {
		orderMapper.finishOrderList(orderCode);
	}

	@Cacheable(value="order")
	@LogAnnotation(description="查询一个用户的订单")
	public List<OrderList> listByUser() {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		return orderMapper.listByUser(user.getId());
	}
	
	@Cacheable(value="order")
	@LogAnnotation(description="获取最新添加的订单")
	public OrderList getRecent() {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		List<OrderList> orders = orderMapper.listByUser(user.getId());
		return orders.get(orders.size() - 1);
	}

	@Cacheable(value="order")
	@LogAnnotation(description="根据订单状态查询订单")
	public List<OrderList> listByState(Integer state) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		return orderMapper.listByState(state, user.getId());
	}
	
	@LogAnnotation(description="计算订单价格")
	public PriceVo getTotalPrice(String orderCode) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		OrderList order = orderMapper.getByOrderCode(orderCode, user.getId());
		BigDecimal totalPrice = new BigDecimal(0);
		List<OrderProduct> orderProducts = order.getProducts();
		for (OrderProduct orderProduct : orderProducts) {
			Product product = orderProduct.getProduct();
			Integer number = orderProduct.getBuyNumber();
			BigDecimal simpleTotal = product.getPrice().multiply(new BigDecimal(number));
			totalPrice = totalPrice.add(simpleTotal);
		}
		return new PriceVo(totalPrice, order.getPostPrice());
	}

	
	@LogAnnotation(description="查询订单")
	public PageResult<OrderQueryVo> query(OrderQuery query) {
		Integer begin = (query.getCurrent() - 1) * 11;
		List<OrderList> orders= orderMapper.query(query, new RowBounds(begin, 11));
		Integer count = orderMapper.getCount(query);
		List<OrderQueryVo> vos = new ArrayList<>();
		for (OrderList order : orders) {
			vos.add(new OrderQueryVo(order));
		}
		return new PageResult<>(vos, count, 11, query.getCurrent());
	}
}

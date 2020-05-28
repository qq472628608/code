package com.fjw.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.OrderList;
import com.fjw.domain.query.OrderQuery;
import com.fjw.domain.query.PageResult;
import com.fjw.dto.ProductDto;
import com.fjw.service.IOrderService;
import com.fjw.util.ThreadLocalHandler;
import com.fjw.vo.OrderQueryVo;
import com.fjw.vo.OrderVo;
import com.fjw.vo.PriceVo;

import lombok.Setter;

@RestController
public class OrderController {

	@Setter@Autowired
	private IOrderService orderService;
	
	@RequestMapping(value="/order/{orderCode}",method=RequestMethod.GET)
	public OrderVo getByOrderCode(@PathVariable("orderCode")String orderCode,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return new OrderVo(orderService.getByOrderCode(orderCode));
	}
	
	@RequestMapping(value="/orders/user",method=RequestMethod.GET)
	public List<OrderVo> listByUser(HttpServletRequest request){
		ThreadLocalHandler.setLocalRequest(request);
		List<OrderVo> orders = new ArrayList<>();
		for (OrderList order : orderService.listByUser()) {
			orders.add(new OrderVo(order));
		}
		return orders;
	}
	
	@RequestMapping(value="/order",method=RequestMethod.GET)
	public OrderVo getRecent(HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return new OrderVo(orderService.getRecent());
	}
	
	@RequestMapping(value="/order",method=RequestMethod.POST)
	public String save(@Valid@RequestBody List<ProductDto> ops,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return orderService.save(ops);
	}

	@RequestMapping(value="/order/{id}",method=RequestMethod.DELETE)
	public List<OrderVo> delete(@PathVariable("id")Long id,HttpServletRequest request){
		ThreadLocalHandler.setLocalRequest(request);
		List<OrderVo> orders = new ArrayList<>();
		for (OrderList order : orderService.delete(id)) {
			orders.add(new OrderVo(order));
		}
		return orders;
	}
	
	@RequestMapping(value="/orders/state/{state}",method=RequestMethod.GET)
	public List<OrderVo> listByState(@PathVariable("state")Integer state,HttpServletRequest request){
		ThreadLocalHandler.setLocalRequest(request);
		List<OrderVo> orders = new ArrayList<>();
		for (OrderList order : orderService.listByState(state)) {
			orders.add(new OrderVo(order));
		}
		return orders;
	}
	
	@RequestMapping(value="/order/totalPrice",method=RequestMethod.GET)
	public PriceVo getTotalPrice(String orderCode,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return orderService.getTotalPrice(orderCode);
	}
	
	@RequestMapping(value="/orders",method=RequestMethod.PUT)
	public PageResult<OrderQueryVo> query(OrderQuery query) {
		return orderService.query(query);
	}
	
	@RequestMapping(value="/order/finish",method=RequestMethod.PUT)
	public void finishOrder(@RequestBody String orderCode) {
		System.out.println(orderCode);
		orderService.finishOrder(orderCode);
	}
}

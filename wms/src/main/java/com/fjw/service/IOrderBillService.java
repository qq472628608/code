package com.fjw.service;

import java.util.List;

import com.fjw.domain.OrderBill;
import com.fjw.query.OrderBillQuery;
import com.fjw.query.PageQuery;

public interface IOrderBillService {
	public void save(OrderBill orderBill);
	public void update(OrderBill orderBill);
	public void delete(Long id);
	public OrderBill get(Long id);
	public List<OrderBill> list();
	public PageQuery query(OrderBillQuery bq);
	public void audit(Long id);
}

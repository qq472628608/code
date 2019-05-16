package com.fjw.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.IOrderBillDAO;
import com.fjw.domain.OrderBill;
import com.fjw.domain.OrderBillItem;
import com.fjw.query.OrderBillQuery;
import com.fjw.query.PageQuery;
import com.fjw.service.IOrderBillService;
import com.fjw.util.UserContext;

import lombok.Setter;

public class OrderBillServiceImpl implements IOrderBillService{
	@Setter@Autowired
	private IOrderBillDAO orderBillDAO;
	public void save(OrderBill orderBill) {
		orderBill.setInputUser(UserContext.getCurrentEmployee());
		orderBill.setInputTime(new Date());
		orderBill.setStatus(0);
		parseItem(orderBill);
		orderBillDAO.save(orderBill);
	}

	public void update(OrderBill orderBill) {
		orderBill = orderBillDAO.get(orderBill.getId());
		if(orderBill.getStatus() == 0) {
			parseItem(orderBill);
			orderBillDAO.update(orderBill);
		}
	}

	public void delete(Long id) {
		OrderBill bill = orderBillDAO.get(id);
		if(bill.getStatus() == 0) {
			orderBillDAO.delete(id);
		}
	}

	public OrderBill get(Long id) {
		return orderBillDAO.get(id);
	}

	public List<OrderBill> list() {
		return orderBillDAO.list();
	}

	public PageQuery query(OrderBillQuery bq) {
		return orderBillDAO.query(bq);
	}
	
	public void audit(Long id) {
		OrderBill bill = orderBillDAO.get(id);
		if(bill.getStatus() == 0) {
			bill.setAuditor(UserContext.getCurrentEmployee());
			bill.setAuditTime(new Date());
			bill.setStatus(1);
			orderBillDAO.update(bill);
		}
	}
	
	private void parseItem(OrderBill orderBill) {
		orderBill.setTotalAmount(BigDecimal.ZERO);
		orderBill.setTotalNumber(BigDecimal.ZERO);
		for(OrderBillItem item:orderBill.getItems()) {
			item.setBill(orderBill);
			item.setAmount(item.getCostPrice().multiply(item.getNumber()).setScale(2,RoundingMode.HALF_UP));
			orderBill.setTotalNumber(orderBill.getTotalNumber().add(item.getNumber()));
			orderBill.setTotalAmount(orderBill.getTotalAmount().add(item.getAmount()));
		}
	}
}

package com.fjw.service;

import java.util.List;

import com.fjw.domain.StockIncomeBill;
import com.fjw.query.PageQuery;
import com.fjw.query.StockIncomeBillQuery;

public interface IStockIncomeBillService {
	public void save(StockIncomeBill bill);
	public void update(StockIncomeBill bill);
	public void delete(Long id);
	public StockIncomeBill get(Long id);
	public List<StockIncomeBill> list();
	public PageQuery query(StockIncomeBillQuery bq);
	public void audit(StockIncomeBill bill);
}

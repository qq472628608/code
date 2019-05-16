package com.fjw.service;

import java.util.List;

import com.fjw.domain.StockOutcomeBill;
import com.fjw.query.PageQuery;
import com.fjw.query.StockOutcomeBillQuery;

public interface IStockOutcomeBillService {
	public void save(StockOutcomeBill stockOutcomeBill);
	public void update(StockOutcomeBill stockOutcomeBill);
	public void delete(Long id);
	public StockOutcomeBill get(Long id);
	public List<StockOutcomeBill> list();
	public PageQuery query(StockOutcomeBillQuery bq);
	public void audit(StockOutcomeBill stockOutcomeBill);
}

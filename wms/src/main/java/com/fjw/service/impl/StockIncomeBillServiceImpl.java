package com.fjw.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.dao.IProductStockDAO;
import com.fjw.dao.IStockIncomeBillDAO;
import com.fjw.domain.ProductStock;
import com.fjw.domain.StockIncomeBill;
import com.fjw.domain.StockIncomeBillItem;
import com.fjw.query.PageQuery;
import com.fjw.query.StockIncomeBillQuery;
import com.fjw.service.IStockIncomeBillService;
import com.fjw.util.UserContext;

import lombok.Setter;

public class StockIncomeBillServiceImpl implements IStockIncomeBillService {
	@Setter
	@Autowired
	private IStockIncomeBillDAO stockIncomeBillDAO;
	@Setter
	@Autowired
	private IProductStockDAO productStockDAO;

	public void save(StockIncomeBill bill) {
		bill.setInputUser(UserContext.getCurrentEmployee());
		bill.setInputTime(new Date());
		bill.setStatus(0);
		parseItem(bill);
		stockIncomeBillDAO.save(bill);
	}

	public void update(StockIncomeBill bill) {
		bill = stockIncomeBillDAO.get(bill.getId());
		if (bill.getStatus() == 0) {
			parseItem(bill);
			stockIncomeBillDAO.update(bill);
		}
	}

	public void delete(Long id) {
		StockIncomeBill bill = stockIncomeBillDAO.get(id);
		if (bill.getStatus() == 0) {
			stockIncomeBillDAO.delete(id);
		}
	}

	public StockIncomeBill get(Long id) {
		return stockIncomeBillDAO.get(id);
	}

	public List<StockIncomeBill> list() {
		return stockIncomeBillDAO.list();
	}

	public PageQuery query(StockIncomeBillQuery bq) {
		return stockIncomeBillDAO.query(bq);
	}

	public void audit(StockIncomeBill bill) {
		bill = stockIncomeBillDAO.get(bill.getId());
		if (bill.getStatus() == 0) {
			List<StockIncomeBillItem> items = bill.getItems();
			for (StockIncomeBillItem item : items) {
				ProductStock productStock = productStockDAO.getProductStockByProductAndDepot(item.getProduct().getId(),
						bill.getDepot().getId());
				if (productStock == null) {
					productStock = new ProductStock();
					productStock.setPrice(item.getCostPrice());
					productStock.setAmount(item.getAmount());
					productStock.setStoreNumber(item.getNumber());
					productStock.setProduct(item.getProduct());
					productStock.setDepot(bill.getDepot());
					productStock.setIncomeDate(new Date());
					productStockDAO.save(productStock);
				} else {
					productStock.setPrice(productStock.getPrice().add(item.getCostPrice()).divide(new BigDecimal(2), 2,
							RoundingMode.HALF_UP));
					productStock.setAmount(productStock.getAmount().add(item.getAmount()));
					productStock.setStoreNumber(productStock.getStoreNumber().add(item.getNumber()));
					productStockDAO.update(productStock);
				}
			}
			bill.setStatus(1);
			bill.setAuditor(UserContext.getCurrentEmployee());
			bill.setAuditTime(new Date());
			stockIncomeBillDAO.update(bill);
		}
	}

	public void parseItem(StockIncomeBill bill) {
		bill.setTotalAmount(BigDecimal.ZERO);
		bill.setTotalNumber(BigDecimal.ZERO);
		List<StockIncomeBillItem> items = bill.getItems();
		for (StockIncomeBillItem item : items) {
			item.setBill(bill);
			item.setAmount(item.getNumber().multiply(item.getCostPrice()).setScale(2, RoundingMode.HALF_UP));
			bill.setTotalAmount(bill.getTotalAmount().add(item.getAmount()));
			bill.setTotalNumber(bill.getTotalNumber().add(item.getNumber()));
		}
	}

}

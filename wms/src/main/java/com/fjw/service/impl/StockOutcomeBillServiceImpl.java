package com.fjw.service.impl;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.Date;
import java.util.List;


import com.fjw.dao.IProductStockDAO;
import com.fjw.dao.ISaleAccountDAO;
import com.fjw.dao.IStockOutcomeBillDAO;
import com.fjw.domain.ProductStock;
import com.fjw.domain.SaleAccount;
import com.fjw.domain.StockOutcomeBill;
import com.fjw.domain.StockOutcomeBillItem;
import com.fjw.query.PageQuery;
import com.fjw.query.StockOutcomeBillQuery;
import com.fjw.service.IStockOutcomeBillService;
import com.fjw.util.UserContext;

import lombok.Setter;

public class StockOutcomeBillServiceImpl implements IStockOutcomeBillService{
	@Setter
	private IStockOutcomeBillDAO stockOutcomeBillDAO;
	@Setter
	private IProductStockDAO productStockDAO; 
	@Setter
	private ISaleAccountDAO saleAccountDAO;
	public void save(StockOutcomeBill stockOutcomeBill) {
		stockOutcomeBill.setInputUser(UserContext.getCurrentEmployee());
		stockOutcomeBill.setInputTime(new Date());
		stockOutcomeBill.setStatus(0);
		parseItem(stockOutcomeBill);
		stockOutcomeBillDAO.save(stockOutcomeBill);
	}

	public void update(StockOutcomeBill stockOutcomeBill) {
		stockOutcomeBill = stockOutcomeBillDAO.get(stockOutcomeBill.getId());
		if(stockOutcomeBill.getStatus() == 0) {
			parseItem(stockOutcomeBill);
			stockOutcomeBillDAO.update(stockOutcomeBill);
		}
	}
	
	public void delete(Long id) {
		StockOutcomeBill stockOutcomeBill = stockOutcomeBillDAO.get(id);
		if(stockOutcomeBill.getStatus() == 0) {
			stockOutcomeBillDAO.delete(id);
		}
	}

	public StockOutcomeBill get(Long id) {
		return stockOutcomeBillDAO.get(id);
	}

	public List<StockOutcomeBill> list() {
		return stockOutcomeBillDAO.list();
	}

	public PageQuery query(StockOutcomeBillQuery bq) {
		return stockOutcomeBillDAO.query(bq);
	}

	public void audit(StockOutcomeBill stockOutcomeBill) {
		stockOutcomeBill  = stockOutcomeBillDAO.get(stockOutcomeBill.getId());
		if(stockOutcomeBill.getStatus() == 0) {
			stockOutcomeBill.setStatus(1);
			stockOutcomeBill.setAuditor(UserContext.getCurrentEmployee());
			stockOutcomeBill.setAuditTime(new Date());
			List<StockOutcomeBillItem> items = stockOutcomeBill.getItems();
			for (StockOutcomeBillItem item : items) {
				ProductStock productStock = productStockDAO.getProductStockByProductAndDepot(item.getProduct().getId(), stockOutcomeBill.getDepot().getId());
				//对应仓库中没有对应商品的库存
				if(productStock == null) {
					throw new RuntimeException(stockOutcomeBill.getDepot().getName()+"没有"+item.getProduct().getName()+"的库存");
				}
				//对应仓库中对应商品的库存不足
				if(item.getNumber().compareTo(productStock.getStoreNumber()) > 0) {
					throw new RuntimeException(stockOutcomeBill.getDepot().getName()+"中"+item.getProduct().getName()+"的库存不足（当前库存："+productStock.getStoreNumber()+"）");
				}
				//对应仓库中的对应商品的库存足够
				if(item.getNumber().compareTo(productStock.getStoreNumber()) <= 0) {
					productStock.setStoreNumber(productStock.getStoreNumber().subtract(item.getNumber()));
					productStock.setAmount(productStock.getStoreNumber().multiply(productStock.getPrice()).setScale(2, RoundingMode.HALF_UP));
					productStock.setOutcomeDate(stockOutcomeBill.getAuditTime());
					//存储销售账单
					SaleAccount saleAccount = new SaleAccount();
					saleAccount.setVdate(stockOutcomeBill.getAuditTime());
					saleAccount.setNumber(item.getNumber());
					saleAccount.setCostPrice(productStock.getPrice());
					saleAccount.setCostAmount(saleAccount.getNumber().multiply(saleAccount.getCostPrice()).setScale(2, RoundingMode.HALF_UP));
					saleAccount.setSalePrice(item.getSalePrice());
					saleAccount.setSaleAmount(saleAccount.getNumber().multiply(saleAccount.getSalePrice()).setScale(2, RoundingMode.HALF_UP));
					saleAccount.setProduct(item.getProduct());
					saleAccount.setSaleMan(stockOutcomeBill.getInputUser());
					saleAccount.setClient(stockOutcomeBill.getClient());
					saleAccountDAO.save(saleAccount);
				}
			}
			stockOutcomeBillDAO.update(stockOutcomeBill);
		}
	}
	
	public void parseItem(StockOutcomeBill stockOutcomeBill) {
		stockOutcomeBill.setTotalAmount(BigDecimal.ZERO);
		stockOutcomeBill.setTotalNumber(BigDecimal.ZERO);
		List<StockOutcomeBillItem> items = stockOutcomeBill.getItems();
		for (StockOutcomeBillItem item : items) {
			item.setBill(stockOutcomeBill);
			item.setAmount(item.getNumber().multiply(item.getSalePrice()).setScale(2, RoundingMode.HALF_UP));
			stockOutcomeBill.setTotalAmount(stockOutcomeBill.getTotalAmount().add(item.getAmount()));
			stockOutcomeBill.setTotalNumber(stockOutcomeBill.getTotalNumber().add(item.getNumber()));
		}
	}
}

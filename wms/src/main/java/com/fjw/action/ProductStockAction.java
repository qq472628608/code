package com.fjw.action;

import com.fjw.query.ProductStockQuery;
import com.fjw.service.IBrandService;
import com.fjw.service.IDepotService;
import com.fjw.service.IProductStockService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;

import lombok.Getter;
import lombok.Setter;

public class ProductStockAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private IProductStockService productStockService;
	@Setter
	private IDepotService depotService;
	@Setter
	private IBrandService brandService;
	@Setter@Getter
	private ProductStockQuery bq = new ProductStockQuery();
	
	@RequiredPermission("库存报表")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", productStockService.query(bq));
			ActionContext.getContext().put("depots", depotService.list());
			ActionContext.getContext().put("brands", brandService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
}

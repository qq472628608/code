package com.fjw.action;

import com.fjw.domain.StockOutcomeBill;
import com.fjw.query.StockOutcomeBillQuery;
import com.fjw.service.IClientService;
import com.fjw.service.IDepotService;
import com.fjw.service.IStockOutcomeBillService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class StockOutcomeBillAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private IStockOutcomeBillService stockOutcomeBillService;
	@Setter
	private IDepotService depotService;
	@Setter
	private IClientService clientService;
	@Setter@Getter
	private StockOutcomeBillQuery bq = new StockOutcomeBillQuery();
	@Setter@Getter
	private StockOutcomeBill stockOutcomeBill = new StockOutcomeBill();
	
	@RequiredPermission("销售出库列表")
	@InputConfig(methodName="edit")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", stockOutcomeBillService.query(bq));
			ActionContext.getContext().put("depots", depotService.list());
			ActionContext.getContext().put("clients", clientService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	
	@RequiredPermission("销售出库编辑")
	public String edit() throws Exception {
		ActionContext.getContext().put("depots", depotService.list());
		ActionContext.getContext().put("clients", clientService.list());
		if(stockOutcomeBill.getId() != null) {
			this.stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
		}
		return "edit";
	}
	
	@RequiredPermission("销售出库保存或更新")
	public String saveOrUpdate() {
		try {
			if(stockOutcomeBill.getId() == null) {
				stockOutcomeBillService.save(stockOutcomeBill);
			}else {
				stockOutcomeBillService.update(stockOutcomeBill);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	
	@RequiredPermission("销售出库删除")
	public String delete() throws Exception{
		stockOutcomeBillService.delete(stockOutcomeBill.getId());
		return NONE;
	}
	
	@RequiredPermission("销售出库查看")
	public String view() throws Exception{
		this.stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
		return "view";
	}
	
	@RequiredPermission("销售出库审核")
	public String audit() throws Exception{
		stockOutcomeBillService.audit(stockOutcomeBill);
		return "audit";
	}
	
	public void prepareSaveOrUpdate() {
		if(stockOutcomeBill.getId() != null) {
			this.stockOutcomeBill = stockOutcomeBillService.get(stockOutcomeBill.getId());
			stockOutcomeBill.setDepot(null);
			stockOutcomeBill.setClient(null);
		}
		stockOutcomeBill.getItems().clear();
	}
}

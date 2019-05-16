package com.fjw.action;

import com.fjw.domain.StockIncomeBill;
import com.fjw.query.StockIncomeBillQuery;
import com.fjw.service.IDepotService;
import com.fjw.service.IStockIncomeBillService;
import com.fjw.service.ISupplierService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class StockIncomeBillAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private IStockIncomeBillService stockIncomeBillService;
	@Setter
	private ISupplierService supplierService;
	@Setter
	private IDepotService depotService;
	@Setter@Getter
	private StockIncomeBillQuery bq = new StockIncomeBillQuery();
	@Setter@Getter
	private StockIncomeBill stockIncomeBill = new StockIncomeBill();
	
	
	@InputConfig(methodName="edit")
	@RequiredPermission("采购入库列表")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", stockIncomeBillService.query(bq));
			ActionContext.getContext().put("suppliers", supplierService.list());
			ActionContext.getContext().put("depots", depotService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	@RequiredPermission("采购入库编辑")
	public String edit() throws Exception{
		ActionContext.getContext().put("depots", depotService.list());
		ActionContext.getContext().put("suppliers", supplierService.list());
		if(stockIncomeBill.getId() != null) {
			this.stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
		}
		return "edit";
	}
	@RequiredPermission("采购入库保存或更新")
	public String saveOrUpdate() {
		try {
			if(stockIncomeBill.getId() == null) {
				stockIncomeBillService.save(stockIncomeBill);
			}else {
				stockIncomeBillService.update(stockIncomeBill);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	@RequiredPermission("采购入库删除")
	public String delete() throws Exception{
		stockIncomeBillService.delete(this.stockIncomeBill.getId());
		return NONE;
	}
	
	@RequiredPermission("采购入库审核")
	public String audit() throws Exception{
		stockIncomeBillService.audit(stockIncomeBill);
		return "audit";
	}
	@RequiredPermission("采购入库查看")
	public String view() throws Exception{
		stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
		return "view";
	}
	public void prepareSaveOrUpdate() {
		if(stockIncomeBill.getId() != null) {
			this.stockIncomeBill = stockIncomeBillService.get(stockIncomeBill.getId());
			stockIncomeBill.setSupplier(null);
			stockIncomeBill.setDepot(null);
			stockIncomeBill.getItems().clear();
		}
	}
}

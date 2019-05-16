package com.fjw.action;


import com.fjw.domain.OrderBill;
import com.fjw.query.OrderBillQuery;
import com.fjw.service.IOrderBillService;
import com.fjw.service.ISupplierService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class OrderBillAction extends BaseAction {

	private static final long serialVersionUID = 1L;
	@Setter
	private IOrderBillService orderBillService;
	@Setter
	private ISupplierService supplierService;
	@Setter@Getter
	private OrderBillQuery bq = new OrderBillQuery();
	@Setter@Getter
	private OrderBill orderBill = new OrderBill();

	@InputConfig(methodName = "edit")
	@RequiredPermission("采购订单列表")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", orderBillService.query(bq));
			ActionContext.getContext().put("suppliers", supplierService.list());
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}

	@RequiredPermission("采购订单编辑")
	public String edit() throws Exception {
		ActionContext.getContext().put("suppliers", supplierService.list());
		if (orderBill.getId() != null) {
			this.orderBill = orderBillService.get(orderBill.getId());
		}
		return "edit";
	}

	@RequiredPermission("采购订单保存或更新")
	public String saveOrUpdate() {
		try {
			if (orderBill.getId() == null) {
				orderBillService.save(orderBill);
			} else {
				orderBillService.update(orderBill);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	
	@RequiredPermission("采购订单删除")
	public String delete() throws Exception{
		orderBillService.delete(orderBill.getId());
		return NONE;
	}
	
	@RequiredPermission("采购订单审核")
	public String audit() throws Exception{
		ActionContext.getContext().put("suppliers", supplierService.list());
		orderBillService.audit(orderBill.getId());
		return "audit";
	}
	
	@RequiredPermission("采购订单查看")
	public String view() throws Exception{
		orderBill = orderBillService.get(orderBill.getId());
		return "view";
	}
	
	public void prepareSaveOrUpdate() {
		if(orderBill.getId() != null) {
			orderBill = orderBillService.get(orderBill.getId());
			orderBill.setSupplier(null);
			orderBill.getItems().clear();
		}
	}
	
	
}

package com.fjw.action;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.domain.Supplier;
import com.fjw.query.BaseQuery;
import com.fjw.service.ISupplierService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;

import lombok.Getter;
import lombok.Setter;

public class SupplierAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter@Autowired
	private ISupplierService supplierService;
	@Setter@Getter
	private BaseQuery bq = new BaseQuery();
	@Setter@Getter
	private Supplier supplier = new Supplier();
	@Setter
	private List<Long> ids = new ArrayList<>();
	@RequiredPermission("供应商列表")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", supplierService.query(bq));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	@RequiredPermission("供应商编辑")
	public String edit() throws Exception{
		if(supplier.getId() != null) {
			this.supplier = supplierService.get(supplier.getId());
		}
		return "edit";
	}
	@RequiredPermission("供应商保存或更新")
	public String saveOrUpdate() {
		try {
			if(supplier.getId() == null) {
				supplierService.save(supplier);
			}else {
				supplierService.update(supplier);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	@RequiredPermission("供应商删除")
	public String delete() throws Exception{
		supplierService.delete(supplier.getId());
		return NONE;
	}
	@RequiredPermission("供应商批量删除")
	public String bathDelete() throws Exception{
		supplierService.bathDelete(ids);
		return NONE;
	}
}

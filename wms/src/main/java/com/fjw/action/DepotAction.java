package com.fjw.action;

import com.fjw.domain.Depot;
import com.fjw.query.BaseQuery;
import com.fjw.service.IDepotService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class DepotAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private IDepotService depotService;
	@Setter@Getter
	private BaseQuery bq = new BaseQuery();
	@Setter@Getter
	private Depot depot = new Depot();
	
	
	@InputConfig(methodName="edit")
	@RequiredPermission("仓库列表")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", depotService.query(bq));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	@RequiredPermission("仓库编辑")
	public String edit() throws Exception{
		if(depot.getId() != null) {
			this.depot = depotService.get(depot.getId());
		}
		return "edit";
	}
	@RequiredPermission("仓库保存或更新")
	public String saveOrUpdate() {
		try {
			if(depot.getId() == null) {
				depotService.save(depot);
			}else {
				depotService.update(depot);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	@RequiredPermission("仓库删除")
	public String delete() throws Exception{
		depotService.delete(depot.getId());
		return NONE;
	}
	public void prepareSaveOrUpdate() {
		if(depot.getId() != null) {
			this.depot = depotService.get(depot.getId());
		}
	}
}

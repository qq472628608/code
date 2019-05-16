package com.fjw.action;

import com.fjw.domain.Client;
import com.fjw.query.ClientQuery;
import com.fjw.service.IClientService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class ClientAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private IClientService clientService;
	@Setter@Getter
	private ClientQuery bq = new ClientQuery();
	@Setter@Getter
	private Client client = new Client();
	
	@InputConfig(methodName="edit")
	@RequiredPermission("客户列表")
	public String list() {
		try {
			ActionContext.getContext().put("pageQuery", clientService.query(bq));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}
	
	@RequiredPermission("客户编辑")
	public String edit() throws Exception{
		if(client.getId() != null) {
			this.client = clientService.get(client.getId());
		}
		return "edit";
	}
	
	@RequiredPermission("客户保存或更新")
	public String saveOrUpdate() {
		try {
			if(client.getId() == null) {
				clientService.save(client);
			}else {
				clientService.update(client);
			}
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "saveOrUpdate";
	}
	
	@RequiredPermission("客户删除")
	public String delete() throws Exception{
		clientService.delete(client.getId());
		return NONE;
	}
}

package com.fjw.action;



import java.util.ArrayList;
import java.util.List;

import com.fjw.Vo.SystemMenuVo;
import com.fjw.domain.SystemMenu;
import com.fjw.query.SystemMenuQuery;
import com.fjw.service.ISystemMenuService;
import com.fjw.util.RequiredPermission;
import com.opensymphony.xwork2.ActionContext;
import com.opensymphony.xwork2.interceptor.annotations.InputConfig;

import lombok.Getter;
import lombok.Setter;

public class SystemMenuAction extends BaseAction{

	private static final long serialVersionUID = 1L;
	@Setter
	private ISystemMenuService systemMenuService;
	@Setter@Getter
	private SystemMenuQuery bq = new SystemMenuQuery();
	@Setter@Getter
	private SystemMenu systemMenu = new SystemMenu();
	
	@RequiredPermission("系统菜单列表")
	@InputConfig(methodName="edit")
	public String list() {
		try {
			List<SystemMenuVo> parentList = systemMenuService.queryParentList(bq.getParentId());
			ActionContext.getContext().put("parentList", parentList);
			ActionContext.getContext().put("pageQuery", systemMenuService.query(bq));
		} catch (Exception e) {
			e.printStackTrace();
			addActionError(e.getMessage());
		}
		return "list";
	}


	@RequiredPermission("系统菜单编辑")
	public String edit() {
		if(systemMenu.getId() != null) {
			this.systemMenu = systemMenuService.get(systemMenu.getId());
		}
		if(bq.getParentId() == -1) {
			ActionContext.getContext().put("parentMenu", "根目录");
		}else {
			ActionContext.getContext().put("parentMenu", systemMenuService.get(bq.getParentId()).getName());
		}
		return "edit";
	}
	
	@RequiredPermission("系统菜单保存或更新")
	public String saveOrUpdate() {
		if(bq.getParentId() == -1) {
			this.systemMenu.setParent(null);
		}else {
			SystemMenu parent = systemMenuService.get(bq.getParentId());
			this.systemMenu.setParent(parent);
		}
		if(systemMenu.getId() == null) {
			systemMenuService.save(systemMenu);
		}else {
			systemMenuService.update(systemMenu);
		}
		return "saveOrUpdate";
	}
	
	@RequiredPermission("系统菜单删除")
	public String delete() {
		systemMenuService.delete(systemMenu.getId());
		return NONE;
	}
	public String parentSn() {
		System.out.println(11111);
		List<SystemMenu> menus = new ArrayList<>();
		menus = systemMenuService.getChildrenList(bq.getParentSn());
		for (SystemMenu systemMenu : menus) {
			System.out.println(systemMenu.getName());
		}
		return NONE;
	}
}

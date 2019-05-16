package com.fjw.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.fjw.Vo.SystemMenuVo;
import com.fjw.dao.ISystemMenuDAO;
import com.fjw.domain.SystemMenu;
import com.fjw.query.PageQuery;
import com.fjw.query.SystemMenuQuery;
import com.fjw.service.ISystemMenuService;

import lombok.Setter;

public class SystemMenuServiceImpl implements ISystemMenuService{
	@Setter@Autowired
	private ISystemMenuDAO systemMenuDAO;
	public void save(SystemMenu systemMenu) {
		systemMenuDAO.save(systemMenu);
	}

	public void update(SystemMenu systemMenu) {
		systemMenuDAO.update(systemMenu);
	}

	public void delete(Long id) {
		systemMenuDAO.delete(id);
	}

	public SystemMenu get(Long id) {
		return systemMenuDAO.get(id);
	}

	public List<SystemMenu> list() {
		return systemMenuDAO.list();
	}

	public PageQuery query(SystemMenuQuery bq) {
		return systemMenuDAO.query(bq);
	}

	public List<SystemMenuVo> queryParentList(Long parentId) {
		List<SystemMenuVo> parentList = new ArrayList<>();
		SystemMenu current = systemMenuDAO.get(parentId);
		createParentList(current,parentList);
		Collections.reverse(parentList);
		return parentList;
	}

	private void createParentList(SystemMenu current, List<SystemMenuVo> parentList) {	
		if(current != null) {
			SystemMenuVo parentVo = new SystemMenuVo();
			parentVo.setId(current.getId());
			parentVo.setName(current.getName());
			parentList.add(parentVo);
			createParentList(current.getParent(),parentList);
		}
	}

	public List<SystemMenu> childrenMenus() {
		return systemMenuDAO.childrenMenus();
	}

	public List<SystemMenu> getChildrenList(String parentSn) {
		return systemMenuDAO.getChildrenList(parentSn);
	}
}

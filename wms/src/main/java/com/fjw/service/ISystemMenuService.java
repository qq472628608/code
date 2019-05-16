package com.fjw.service;

import java.util.List;

import com.fjw.Vo.SystemMenuVo;
import com.fjw.domain.SystemMenu;
import com.fjw.query.PageQuery;
import com.fjw.query.SystemMenuQuery;

public interface ISystemMenuService {
	public void save(SystemMenu systemMenu);
	public void update(SystemMenu systemMenu);
	public void delete(Long id);
	public SystemMenu get(Long id);
	public List<SystemMenu> list();
	public PageQuery query(SystemMenuQuery bq);
	public List<SystemMenuVo> queryParentList(Long parentId);
	public List<SystemMenu> childrenMenus();
	public List<SystemMenu> getChildrenList(String parentSn);
}

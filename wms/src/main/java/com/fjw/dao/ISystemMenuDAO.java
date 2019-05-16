package com.fjw.dao;

import java.util.List;

import com.fjw.domain.SystemMenu;

public interface ISystemMenuDAO extends IGeneratorDAO<SystemMenu>{

	public List<SystemMenu> childrenMenus();

	public List<SystemMenu> getChildrenList(String parentSn);

}

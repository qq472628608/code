package com.fjw.service;

import java.util.List;

import com.fjw.domain.Permission;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;

public interface IPermissionService {
	public PageQuery query(BaseQuery bq);
	public void delete(Long id);
	public void reload();
	public void bathDelete(List<Long> ids);
	public List<Permission> list();
}

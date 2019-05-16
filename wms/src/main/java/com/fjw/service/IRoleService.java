package com.fjw.service;

import java.util.List;

import com.fjw.domain.Role;
import com.fjw.query.BaseQuery;
import com.fjw.query.PageQuery;

public interface IRoleService {
	public void save(Role role);
	public void update(Role role);
	public void delete(Long id);
	public Role get(Long id);
	public List<Role> list();
	public PageQuery query(BaseQuery bq);
	public void bathDelete(List<Long> ids);
}

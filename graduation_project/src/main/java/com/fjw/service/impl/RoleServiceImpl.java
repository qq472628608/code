package com.fjw.service.impl;

import java.util.List;

import org.apache.ibatis.session.RowBounds;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.Role;
import com.fjw.domain.User;
import com.fjw.domain.query.UserQuery;
import com.fjw.exception.MyIllegalArgumentException;
import com.fjw.mapper.RoleMapper;
import com.fjw.service.IRoleService;
import com.fjw.util.StringUtil;

import lombok.Setter;

@Service
public class RoleServiceImpl implements IRoleService {
	
	@Setter@Autowired@Lazy
	private RoleMapper roleMapper;
	
	@Transactional(rollbackFor=Exception.class)
	@CacheEvict(value= {"role"},allEntries=true)
	@LogAnnotation(description="添加角色")
	public void saveRole(Role role) throws Exception {
		if(!StringUtil.hasLength(role.getName())) {
			throw new MyIllegalArgumentException("角色信息错误");
		}
		roleMapper.saveRole(role);
	}
	
	@Transactional(rollbackFor=Exception.class)
	@CacheEvict(value="role",allEntries=true)
	@LogAnnotation(description="删除角色")
	public void deleteRole(Long id) {
		roleMapper.deleteRole(id);
	}

	@Cacheable(value="role",key="#id")
	@LogAnnotation(description="查询一个角色")
	public Role getRole(Long id) {
		return roleMapper.getRole(id);
	}

	@CacheEvict(value= {"role"},allEntries=true)
	@Transactional(rollbackFor=Exception.class)
	@LogAnnotation(description="更新角色信息")
	public void updateRole(Role role) throws Exception {
		if(!StringUtil.hasLength(role.getName())) {
			throw new MyIllegalArgumentException("角色信息错误");
		}
	}

	@Cacheable(value="role")
	@LogAnnotation(description="查询当前页的角色")
	public List<Role> list() {
		return roleMapper.list();
	}
	
	@Cacheable(value="role")
	@LogAnnotation(description="根据名称查询角色")
	public Role queryByName(String name) {
		return roleMapper.queryByName(name);
	}

	@Transactional(rollbackFor=Exception.class)
	@CacheEvict(value="role",allEntries=true)
	@LogAnnotation(description="批量删除角色")
	public void bathDelete(Long[] ids) {
		roleMapper.batchDelete(ids);
	}
}

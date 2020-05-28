package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.Collect;
import com.fjw.domain.User;
import com.fjw.mapper.CollectMapper;
import com.fjw.service.ICollectService;
import com.fjw.util.ContextUserUtil;
import com.fjw.util.ThreadLocalHandler;

import lombok.Setter;

@Service
public class CollectServiceImpl implements ICollectService {
	
	@Setter@Autowired@Lazy
	private CollectMapper collectMapper;

	@CacheEvict(value="collect",allEntries=true)
	@Transactional(rollbackFor=Exception.class)
	@LogAnnotation(description="添加收藏")
	public void add(Long product_id) throws Exception {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		Collect collect = collectMapper.get(user.getId(), product_id);
		if(collect != null) {
			throw new IllegalStateException("已添加进收藏");
		}
		collectMapper.collectAdd(user.getId(), product_id);
	}

	@CacheEvict(value="collect",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="删除收藏")
	public List<Collect> delete(Long id) {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		collectMapper.collectDelete(id,user.getId());
		return list();
	}

	@Cacheable(value="collect")
	@LogAnnotation(description="查询当前用户所有收藏")
	public List<Collect> list() {
		User user = ContextUserUtil.getCurrentUser(ThreadLocalHandler.getLocalRequest());
		return collectMapper.list(user.getId());
	}

}

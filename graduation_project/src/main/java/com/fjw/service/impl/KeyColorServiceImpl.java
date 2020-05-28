package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.KeyColor;
import com.fjw.mapper.KeyColorMapper;
import com.fjw.service.IKeyColorService;

import lombok.Setter;

@Service
public class KeyColorServiceImpl implements IKeyColorService {

	@Setter@Autowired@Lazy
	private KeyColorMapper keyColorMapper;
	
	@CacheEvict(value= {"color","img","keyColor","product"},allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="添加颜色商品关联")
	public void add(KeyColor keyColor) {
		keyColorMapper.add(keyColor);
	}

	@CacheEvict(value= {"color","img","keyColor","product"},allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="删除颜色商品关联")
	public void delete(Long id) {
		keyColorMapper.delete(id);
	}

	@Cacheable(value="color")
	@LogAnnotation(description="查询一个商品的所有颜色属性")
	public List<KeyColor> list(Long ka_id) {
		return keyColorMapper.list(ka_id);
	}

	@CacheEvict(value= {"color","img","keyColor","product"},allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="更新商品颜色关联")
	public void update(KeyColor keyColor) {
		keyColorMapper.update(keyColor);
	}

}

package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.KeyValue;
import com.fjw.mapper.KeyValueMapper;
import com.fjw.service.IKeyValueService;

import lombok.Setter;

@Service
public class KeyValueServiceImpl implements IKeyValueService {

	@Setter@Autowired@Lazy
	private KeyValueMapper keyValueMapper;
	
	@CacheEvict(value="keyValue",allEntries=true)
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="添加商品属相关联")
	public void add(Long ka_id, Long av_id) {
		keyValueMapper.add(ka_id, av_id);
	}

	@Cacheable(value="keyValue")
	@LogAnnotation(description="查询所有商品属性关联")
	public List<KeyValue> list(Long ka_id) {
		return keyValueMapper.list(ka_id);
	}
	
	@Cacheable(value="keyValue")
	@LogAnnotation(description="查询一个商品的商品属相关联")
	public List<KeyValue> query(Long ka_id, Long av_id) {
		return keyValueMapper.query(ka_id, av_id);
	}

	@Cacheable(value="keyValue")
	@LogAnnotation(description="根据id查询商品属相关联")
	public KeyValue get(Long id) {
		return keyValueMapper.get(id);
	}
	
	@Cacheable(value="keyValue")
	@LogAnnotation(description="查询key-value-id")
	public Long get(Long ka_id,Long av_id) {
		return keyValueMapper.getByKa(ka_id,av_id);
	}

}

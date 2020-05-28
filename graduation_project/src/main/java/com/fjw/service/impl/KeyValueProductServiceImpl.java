package com.fjw.service.impl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.mapper.KeyValueProductMapper;
import com.fjw.service.IKeyValueProductService;

import lombok.Setter;

@Service
public class KeyValueProductServiceImpl implements IKeyValueProductService {
	
	@Setter@Autowired@Lazy
	private KeyValueProductMapper kvpMapper; 

	@Transactional(rollbackFor=Exception.class)
	@LogAnnotation(description="商品-sku增加")
	public void add(Long[] kvs,Long product_id) {
		if(kvs.length > 0) {
			for(int i = 0;i < kvs.length;i++) {
				kvpMapper.add(product_id, kvs[i]);
			}
		}
	}

	@Transactional(rollbackFor=Exception.class)
	@LogAnnotation(description="商品-sku删除")
	public void delete(Long id) {
		kvpMapper.delete(id);
	}

}

package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.AttributeValue;
import com.fjw.mapper.AttributeValueMapper;
import com.fjw.service.IAttributeValueService;

import lombok.Setter;

@Service
public class AttributeValueServiceImpl implements IAttributeValueService {

	@Setter@Autowired@Lazy
	private AttributeValueMapper avMapper;
	
	@Cacheable(value="attributeValue")
	@LogAnnotation(description="查询所有的属相值")
	public List<AttributeValue> list(Long sa_id) {
		return avMapper.list(sa_id);
	}

}

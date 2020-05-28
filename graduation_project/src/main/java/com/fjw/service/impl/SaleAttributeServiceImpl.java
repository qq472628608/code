package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.SaleAttribute;
import com.fjw.mapper.SaleAttributeMapper;
import com.fjw.service.ISaleAttributeService;

import lombok.Setter;

@Service
public class SaleAttributeServiceImpl implements ISaleAttributeService {

	@Setter@Autowired@Lazy
	private SaleAttributeMapper saMapper;
	
	@Cacheable(value="saleAttribute")
	@LogAnnotation(description="查询所有销售属性")
	public List<SaleAttribute> list(Long kind_id) {
		return saMapper.list(kind_id);
	}

}

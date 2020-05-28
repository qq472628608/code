package com.fjw.service;

import java.util.List;

import com.fjw.domain.SaleAttribute;

public interface ISaleAttributeService {

	public List<SaleAttribute> list(Long kind_id);
	
}

package com.fjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.SaleAttribute;
import com.fjw.service.ISaleAttributeService;

import lombok.Setter;

@RestController
public class SaleAttributeController {

	@Setter@Autowired
	private ISaleAttributeService saleAttributeService;
	
	@RequestMapping(value="/saleAttributes/{kind_id}",method=RequestMethod.GET)
	public List<SaleAttribute> list(@PathVariable("kind_id")Long kind_id){
		return saleAttributeService.list(kind_id);
	}
	
}

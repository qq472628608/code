package com.fjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.AttributeValue;
import com.fjw.service.IAttributeValueService;

import lombok.Setter;

@RestController
public class AttributeValueController {

	@Setter@Autowired
	private IAttributeValueService avService;
	
	@RequestMapping(value="/attributeValues/{sa_id}",method=RequestMethod.GET)
	public List<AttributeValue> list(@PathVariable("sa_id")Long sa_id){
		return avService.list(sa_id);
	}
	
}

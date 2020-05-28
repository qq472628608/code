package com.fjw.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.service.IKeyValueProductService;

import lombok.Setter;

@RestController
public class KeyValueProductController {
	
	@Setter@Autowired
	private IKeyValueProductService kvpService;
	
	@RequestMapping(value="/kvp",method=RequestMethod.POST)
	public void add(Long[] kvs,Long product_id) {
		kvpService.add(kvs, product_id);
	}
	
	@RequestMapping(value="/kvp/{id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("id")Long id) {
		kvpService.delete(id);
	}
	
}

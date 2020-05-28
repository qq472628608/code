package com.fjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.KeyValue;
import com.fjw.service.IKeyValueService;

import lombok.Setter;

@RestController
public class KeyValueController {

	@Setter@Autowired
	private IKeyValueService keyValueService;
	
	@RequestMapping(value="/keyValue/{ka_id}",method=RequestMethod.GET)
	public List<KeyValue> list(@PathVariable("ka_id")Long ka_id){
		return keyValueService.list(ka_id);
	}
	
	@RequestMapping(value="/keyValue",method=RequestMethod.POST)
	public void save(Long ka_id,Long av_id) {
		keyValueService.add(ka_id, av_id);
	}
	
	@RequestMapping(value="/keyValue",method=RequestMethod.GET)
	public Long get(Long ka_id,Long av_id) {
		return keyValueService.get(ka_id, av_id);
	}
}

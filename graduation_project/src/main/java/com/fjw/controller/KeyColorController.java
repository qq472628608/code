package com.fjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.KeyColor;
import com.fjw.service.IKeyColorService;

import lombok.Setter;

@RestController
public class KeyColorController {
	
	@Setter@Autowired
	private IKeyColorService keyColorService;
	
	@RequestMapping(value="/keyColor/{ka_id}",method=RequestMethod.GET)
	public List<KeyColor> list(@PathVariable("ka_id")Long ka_id){
		return keyColorService.list(ka_id);
	}
	
	@RequestMapping(value="/keyColor",method=RequestMethod.POST)
	public void save(@RequestBody KeyColor keyColor) {
		keyColorService.add(keyColor);
	}
}

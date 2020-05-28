package com.fjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.Color;
import com.fjw.service.IColorService;

import lombok.Setter;

@RestController
public class ColorController {

	@Setter@Autowired
	private IColorService colorService;
	
	@RequestMapping(value="/colors",method=RequestMethod.GET)
	public List<Color> list(){
		return colorService.list();
	}
	
	@RequestMapping(value="/color",method=RequestMethod.POST)
	public Long save(Color color) {
		return colorService.save(color);
	}
	
}

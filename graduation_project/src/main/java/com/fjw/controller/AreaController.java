package com.fjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.Area;
import com.fjw.service.IAreaService;

import lombok.Setter;

@RestController
public class AreaController {

	@Setter@Autowired
	private IAreaService areaService;
	
	@RequestMapping(value="/area/province",method=RequestMethod.GET)
	public List<Area> listProvince(){
		return areaService.listProvice();
	}
	
	@RequestMapping(value="/area/city/{province_id}",method=RequestMethod.GET)
	public List<Area> listCity(@PathVariable("province_id")Long province_id){
		return areaService.listCity(province_id);
	}
	
	@RequestMapping(value="/area/county/{city_id}",method=RequestMethod.GET)
	public List<Area> listCounty(@PathVariable("city_id")Long city_id){
		return areaService.listCounty(city_id);
	}
}

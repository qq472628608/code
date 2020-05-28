package com.fjw.service;

import java.util.List;

import com.fjw.domain.Area;

public interface IAreaService {
	
	public List<Area> listProvice();
	
	public List<Area> listCity(Long province_id);
	
	public List<Area> listCounty(Long city_id);
 	
}

package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.Area;
import com.fjw.mapper.AreaMapper;
import com.fjw.service.IAreaService;

import lombok.Setter;

@Service
public class AreaServiceImpl implements IAreaService {
	
	@Setter@Autowired@Lazy
	private AreaMapper areaMapper;

	@Cacheable(value="area")
	@LogAnnotation(description="查询省区")
	public List<Area> listProvice() {
		return areaMapper.listProvince();
	}

	@Cacheable(value="area")
	@LogAnnotation(description="查询市区")
	public List<Area> listCity(Long province_id) {
		if(province_id == null) {
			return null;
		}
		return areaMapper.listCity(province_id);
	}

	@Cacheable(value="area")
	@LogAnnotation(description="查询区县")
	public List<Area> listCounty(Long city_id) {
		if(city_id == null) {
			return null;
		}
		return areaMapper.listCounty(city_id);
	}

}

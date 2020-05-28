package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fjw.domain.Area;

@Mapper
public interface AreaMapper {
	public Area getArea(Long id);
	public List<Area> queryByLevel(Integer level);
	public List<Area> listProvince();
	public List<Area> listCity(@Param("province_id")Long province_id);
	public List<Area> listCounty(@Param("city_id")Long city_id);
}

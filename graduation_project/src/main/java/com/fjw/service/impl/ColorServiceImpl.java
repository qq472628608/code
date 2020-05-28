package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.Color;
import com.fjw.mapper.ColorMapper;
import com.fjw.service.IColorService;

import lombok.Setter;

@Service
public class ColorServiceImpl implements IColorService {

	@Setter@Autowired@Lazy
	private ColorMapper colorMapper;
	
	@Cacheable(value="color")
	@LogAnnotation(description="查询所有颜色")
	public List<Color> list() {
		return colorMapper.listColor();
	}

	@Cacheable(value="color")
	@LogAnnotation(description="添加颜色")
	public Long save(Color color) {
		colorMapper.saveColor(color);
		return color.getId();
	}
	
	

}

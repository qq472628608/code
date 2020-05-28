package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.fjw.domain.KeyColor;

@Mapper
public interface KeyColorMapper {
	public void add(KeyColor keyColor);
	public void delete(Long id);
	public KeyColor get(Long id);
	public List<KeyColor> list(Long ka_id);
	public void update(KeyColor keyColor);
}

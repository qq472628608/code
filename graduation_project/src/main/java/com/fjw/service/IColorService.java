package com.fjw.service;

import java.util.List;

import com.fjw.domain.Color;

public interface IColorService {
	public List<Color> list();
	public Long save(Color color);
}

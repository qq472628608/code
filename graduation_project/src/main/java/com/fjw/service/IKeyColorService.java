package com.fjw.service;

import java.util.List;

import com.fjw.domain.KeyColor;

public interface IKeyColorService {
	public void add(KeyColor keyColor);
	public void delete(Long id);
	public List<KeyColor> list(Long ka_id);
	public void update(KeyColor keyColor);
}

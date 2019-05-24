package com.fjw.mapper;

import java.util.List;

import com.fjw.domain.Kind;

public interface KindMapper {
	public void save(Kind kind);
	public void delete(Long id);
	public Kind get(Integer id);
	public void update(Kind kind);
	public List<Kind> list();
}

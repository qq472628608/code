package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.session.RowBounds;

import com.fjw.domain.Color;

@Mapper
public interface ColorMapper {
	public void saveColor(Color color);
	public void deleteColor(Long id);
	public Color getColor(Long id);
	public void updateColor(Color color);
	public List<Color> queryByName(String name);
	public List<Color> listColor(RowBounds rowBounds);
	public void batchDelete(Long[] ids);
	public List<Color> listColor();
}

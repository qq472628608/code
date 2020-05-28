package com.fjw.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.fjw.domain.GeneralProductImg;

@Mapper
public interface GeneralProductImgMapper {
	public void saveImg(GeneralProductImg img);
	public void deleteImg(Long id);
	public List<GeneralProductImg> queryImgByColor(@Param("key_attribute_id")Long key_attribute_id,@Param("color_id")Long color_id);
	public List<GeneralProductImg> queryImgByState(@Param("key_attribute_id")Long key_attribute_id,@Param("state")Long state);
	public List<GeneralProductImg> list(@Param("key_attribute_id")Long key_attribute_id);
	public List<GeneralProductImg> listByState(Long state);
	public GeneralProductImg getImg(@Param("key_attribute_id")Long key_attribute_id);
	public GeneralProductImg getImgByColor(@Param("key_attribute_id")Long key_attribute_id,@Param("color_id")Long color_id);
	public List<GeneralProductImg> listGroupByColor();
	public void update(@Param("img")GeneralProductImg img);
	public List<GeneralProductImg> listAll();
}

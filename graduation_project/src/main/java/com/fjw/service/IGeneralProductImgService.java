package com.fjw.service;

import java.util.List;

import com.fjw.domain.GeneralProductImg;
import com.fjw.dto.ImgDto;

public interface IGeneralProductImgService {
	public void save(GeneralProductImg img);
	public void saves(ImgDto dto);
	public void delete(Long id);
	public List<GeneralProductImg> queryImgByColor(Long key_attribute,Long color_id);
	public List<GeneralProductImg> queryImgByState(Long key_attribute_id,Long state);
	public List<GeneralProductImg> list(Long key_attribute_id);
	public List<GeneralProductImg> listByState(Long state);
	public List<GeneralProductImg> listGroupByColor();
	public GeneralProductImg getImg(Long key_attribute_id,Long color_id);
	public GeneralProductImg getImg(Long key_attribute_id);
}

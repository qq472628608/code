package com.fjw.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.fjw.annotation.LogAnnotation;
import com.fjw.domain.Color;
import com.fjw.domain.GeneralProductImg;
import com.fjw.dto.ImgDto;
import com.fjw.mapper.GeneralProductImgMapper;
import com.fjw.service.IGeneralProductImgService;

import lombok.Setter;

@Service
public class GeneralProductImgServiceImpl implements IGeneralProductImgService {
	
	@Setter@Autowired@Lazy
	private GeneralProductImgMapper imgMapper;

	@CacheEvict(value= {"img","keyAttribute"})
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="添加图片")
	public void save(GeneralProductImg img) {
		imgMapper.saveImg(img);
	}
	
	@CacheEvict(value= {"img","keyAttribute"})
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="批量添加图片")
	public void saves(ImgDto dto) {
		List<String> urls = dto.getUrls();
		GeneralProductImg img = new GeneralProductImg();
		Color color = new Color();
		color.setId(dto.getColor_id());
		for (String url : urls) {
			img.setColor(color);
			img.setKey_attribute_id(dto.getKey_id());
			img.setState(dto.getState());
			img.setUrl("/"+url);
			save(img);
		}
	}
	
	@CacheEvict(value= {"img","keyAttribute"})
	@Transactional(rollbackFor= {Exception.class})
	@LogAnnotation(description="删除图片")
	public void delete(Long id) {
		imgMapper.deleteImg(id);
	}

	@Cacheable(value="img")
	@LogAnnotation(description="根据颜色查询图片")
	public List<GeneralProductImg> queryImgByColor(Long key_attribute, Long color_id) {
		return imgMapper.queryImgByColor(key_attribute, color_id);
	}

	@Cacheable(value="img")
	@LogAnnotation(description="查询一个商品图片")
	public List<GeneralProductImg> queryImgByState(Long key_attribute_id, Long state) {
		return imgMapper.queryImgByState(key_attribute_id, state);
	}

	@Cacheable(value="img")
	@LogAnnotation(description="查询一个商品所有图片")
	public List<GeneralProductImg> list(Long key_attribute_id) {
		return imgMapper.list(key_attribute_id);
	}

	@Cacheable(value="img")
	@LogAnnotation(description="根据图片的位置查询图片")
	public List<GeneralProductImg> listByState(Long state) {
		return imgMapper.listByState(state);
	}

	@Cacheable(value="img")
	@LogAnnotation(description="查询一个商品的一张图片")
	public GeneralProductImg getImg(Long key_attribute_id, Long color_id) {
		return imgMapper.getImgByColor(key_attribute_id, color_id);
	}

	@Cacheable(value="img")
	@LogAnnotation(description="查询一个商品的代表图片")
	public GeneralProductImg getImg(Long key_attribute_id) {
		return imgMapper.getImg(key_attribute_id);
	}

	@Cacheable(value="img")
	@LogAnnotation(description="根据分类查询所有图片")
	public List<GeneralProductImg> listGroupByColor() {
		return imgMapper.listGroupByColor();
	}

}

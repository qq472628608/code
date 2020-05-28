package com.fjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.GeneralProductImg;
import com.fjw.dto.ImgDto;
import com.fjw.service.IGeneralProductImgService;

import lombok.Setter;

@RestController
public class ImgController {

	@Setter@Autowired
	private IGeneralProductImgService imgService;
	
	@RequestMapping(value="/img/key/{state}",method=RequestMethod.GET)
	public List<GeneralProductImg> listByState(@PathVariable("state")Long state) {
		return imgService.listByState(state);
	}
	
	@RequestMapping(value="/img/color",method=RequestMethod.GET)
	public List<GeneralProductImg> listByColor(Long ka_id,Long color_id){
		return imgService.queryImgByColor(ka_id, color_id);
	}
	
	@RequestMapping(value="/img/state",method=RequestMethod.GET)
	public List<GeneralProductImg> listByState(Long ka_id,Long state){
		return imgService.queryImgByState(ka_id, state);
	}
	
	@RequestMapping(value="/img/color/first",method=RequestMethod.GET)
	public GeneralProductImg getByColor(Long ka_id,Long color_id) {
		return imgService.getImg(ka_id, color_id);
	}
	
	@RequestMapping(value="/img",method=RequestMethod.GET)
	public GeneralProductImg getImg(Long ka_id) {
		return imgService.getImg(ka_id);
	}
	
	@RequestMapping(value="/img/{ka_id}",method=RequestMethod.GET)
	public List<GeneralProductImg> list(@PathVariable("ka_id")Long ka_id){
		return imgService.list(ka_id);
	}
	
	@RequestMapping(value="/imgs",method=RequestMethod.GET)
	public List<GeneralProductImg> listGroupByColor(){
		return imgService.listGroupByColor();
	}
	
	@RequestMapping(value="/imgs",method=RequestMethod.POST)
	public void saves(ImgDto dto) {
		imgService.saves(dto);
	}
}

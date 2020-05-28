package com.fjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.KeyAttribute;
import com.fjw.dto.KeyDto;
import com.fjw.service.IKeyAttributeService;

import lombok.Setter;

@RestController
public class KeyAttributeController {

	@Setter@Autowired
	private IKeyAttributeService keyService;
	
	@RequestMapping(value="/key",method=RequestMethod.GET)
	public List<KeyAttribute> list(){
		return keyService.list();
	}
	
	@RequestMapping(value="/key/kind",method=RequestMethod.GET)
	public List<KeyAttribute> listByKind(Long kind_id,Integer begin,Integer end){
		return keyService.listByKind(kind_id,begin,end);
	}
	
	@RequestMapping(value="/key/kind/desc",method=RequestMethod.GET)
	public List<KeyAttribute> listByKindDesc(Long kind_id,Integer begin,Integer end){
		return keyService.listByKindDesc(kind_id,begin,end);
	}
	
	@RequestMapping(value="/key/{id}",method=RequestMethod.GET)
	public KeyAttribute getAttribute(@PathVariable("id")Long id) {
		return keyService.getKeyAttribute(id);
	}
	
	@RequestMapping(value="/key/name",method=RequestMethod.GET)
	public KeyAttribute queryByName(String name) {
		return keyService.queryByName(name);
	}
	
	@RequestMapping(value="/keys",method=RequestMethod.GET)
	public List<KeyAttribute> listNewest(Integer number){
		return keyService.listNewest(number);
	}
	
	@RequestMapping(value="/keys/price",method=RequestMethod.GET)
	public List<KeyAttribute> listByPrice(Long kind_id,Integer number,String tag){
		return keyService.listByPrice(kind_id, number, tag);
	}
	
	@RequestMapping(value="/keys/name",method=RequestMethod.GET)
	public List<KeyAttribute> listByName(String name){
		return keyService.listByName(name);
	}
	
	@RequestMapping(value="/key",method=RequestMethod.POST)
	public Long save(KeyDto dto) {
		return keyService.save(dto);
	} 
}

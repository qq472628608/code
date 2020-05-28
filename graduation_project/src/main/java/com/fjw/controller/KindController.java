package com.fjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.ProductKind;
import com.fjw.domain.query.PageResult;
import com.fjw.service.IProductKindService;

import lombok.Setter;

@RestController
public class KindController {

	@Setter@Autowired
	private IProductKindService kindService;
	
	@RequestMapping(value="/kinds",method=RequestMethod.GET)
	public List<ProductKind> listBase(){
		return kindService.list();
	}
	
	@RequestMapping(value="/kinds",method=RequestMethod.PUT)
	public PageResult<ProductKind> query(String name,Integer current){
		System.out.println(name);
		System.out.println(current);
		return kindService.list(name, current);
	}
	
	
	@RequestMapping(value="/kind/{id}",method=RequestMethod.DELETE)
	public void delete(Long id) {
		kindService.delete(id);
	}
	
	@RequestMapping(value="/kind/{id}",method=RequestMethod.GET)
	public ProductKind get(@PathVariable("id")Long id) {
		return kindService.get(id);
	}
	
	@RequestMapping(value="/kind",method=RequestMethod.POST)
	public void add(ProductKind kind) {
		kindService.save(kind);
	}
	
	@RequestMapping(value="/kind",method=RequestMethod.PUT)
	public void update(ProductKind kind) {
		kindService.update(kind);
	}
	
	
}

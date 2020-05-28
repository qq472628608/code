package com.fjw.controller;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.ShoppingCar;
import com.fjw.dto.ProductDto;
import com.fjw.service.ICarService;
import com.fjw.util.ThreadLocalHandler;

import lombok.Setter;

@RestController
public class CarController {
	
	@Setter@Autowired@Lazy
	private ICarService carService;
	
	@RequestMapping(value="/car",method=RequestMethod.GET)
	public ShoppingCar get(HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return carService.get();
	}
	
	@RequestMapping(value="/car",method=RequestMethod.POST)
	public void add(@Valid@RequestBody ProductDto dto,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		carService.addProduct(dto);
	}
	
	@RequestMapping(value="/car/{product_id}",method=RequestMethod.DELETE)
	public void delete(@PathVariable("product_id")Long product_id,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		carService.deleteProduct(product_id);
	}
	
	@RequestMapping(value="/car",method=RequestMethod.PUT)
	public void update(@Valid@RequestBody ProductDto dto,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		carService.updateProduct(dto);
	}
	
	@RequestMapping(value="/car/number",method=RequestMethod.GET)
	public Integer getNumber(HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return carService.getNumber();
	}
	
	@RequestMapping(value="/car",method=RequestMethod.DELETE)
	public void batchDelete(Long[] ids,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		carService.deleteProducts(ids);
	}
}

package com.fjw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.Address;
import com.fjw.dto.AddressDto;
import com.fjw.service.IAddressService;
import com.fjw.util.ThreadLocalHandler;

import lombok.Setter;

@RestController
public class AddressController {
	
	@Setter@Autowired
	private IAddressService addressService;
	
	@RequestMapping(value="/addresses/user",method=RequestMethod.GET)
	public List<Address> listByUser(HttpServletRequest request){
		ThreadLocalHandler.setLocalRequest(request);
		return addressService.listByUser();
	}
	
	@RequestMapping(value="/address",method=RequestMethod.POST)
	public List<Address> save(@Valid AddressDto dto,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return addressService.save(dto);
	}
	
	@RequestMapping(value="/address/{id}",method=RequestMethod.PUT)
	public List<Address> setDefault(@PathVariable("id")Long id,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return addressService.setDefault(id);
	}
	
	@RequestMapping(value="/address/{id}",method=RequestMethod.GET)
	public Address get(@PathVariable("id")Long id,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return addressService.getAddress(id);
	}
	
	@RequestMapping(value="/address",method=RequestMethod.PUT)
	public List<Address> update(@Valid AddressDto dto,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return addressService.updateAddress(dto);
	}

	@RequestMapping(value="/address/{id}",method=RequestMethod.DELETE)
	public List<Address> delete(@PathVariable("id")Long id,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return addressService.delete(id);
	}
}

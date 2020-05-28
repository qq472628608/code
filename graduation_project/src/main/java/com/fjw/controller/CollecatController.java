package com.fjw.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.Collect;
import com.fjw.service.ICollectService;
import com.fjw.util.ThreadLocalHandler;

import lombok.Setter;

@RestController
public class CollecatController {

	@Setter@Autowired
	private ICollectService collectService;
	
	@RequestMapping(value="/collect",method=RequestMethod.POST)
	public void add(Long product_id,HttpServletRequest request) throws Exception {
		ThreadLocalHandler.setLocalRequest(request);
		collectService.add(product_id);
	}
	
	@RequestMapping(value="/collects",method=RequestMethod.GET)
	public List<Collect> list(HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return collectService.list();
	}
	
	@RequestMapping(value="/collect/{id}",method=RequestMethod.DELETE)
	public List<Collect> delete(@PathVariable("id")Long id,HttpServletRequest request) {
		ThreadLocalHandler.setLocalRequest(request);
		return collectService.delete(id);
	}
	
}

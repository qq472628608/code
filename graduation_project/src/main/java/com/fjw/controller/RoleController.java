package com.fjw.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fjw.domain.Role;
import com.fjw.service.IRoleService;

import lombok.Setter;

@RestController
public class RoleController {
	
	@Setter@Autowired
	private IRoleService roleService;
	
	
	@RequestMapping(value="/roles",method=RequestMethod.GET)
	public List<Role> list(){
		return roleService.list();
	}
	
}

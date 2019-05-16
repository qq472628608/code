package com.fjw.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
@Getter@Setter
public class Role {
	private Long id;
	private String name;
	private String sn;
	Set<Permission> permissions = new HashSet<>();
	Set<Employee> employees = new HashSet<>();
	Set<SystemMenu> systemMenus = new HashSet<>();
}

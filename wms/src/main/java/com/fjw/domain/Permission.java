package com.fjw.domain;


import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Permission {
	private Long id;
	private String name;
	private String expression;
	private Set<Role> roles = new HashSet<>(); 
	@Override
	public String toString() {
		return "Permission [id=" + id + ", name=" + name + ", expression=" + expression + "]";
	}
}

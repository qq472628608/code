package com.fjw.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Department {
	private Long id;
	private String name;
	private String sn;
	private Set<Employee> employees = new HashSet<>();
	@Override
	public String toString() {
		return "Department [id=" + id + ", name=" + name + ", sn=" + sn + "]";
	}
	
}

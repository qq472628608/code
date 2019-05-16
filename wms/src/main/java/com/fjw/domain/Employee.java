package com.fjw.domain;


import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Getter@Setter
public class Employee {
	private Long id;
	private String name;
	private String password;
	private String email;
	private Integer age;
	private boolean admin;
	private Department dept;
	private Set<Role> roles = new HashSet<>();

	public String toString() {
		return "Employee [id=" + id + ", name=" + name + ", password=" + password + ", email=" + email + ", age=" + age
				+ ", admin=" + admin + ", dept=" + dept + ", roles=" + roles + "]";
	}
	
	public boolean getAdmin() {
		return this.admin;
	}

	public String getRoleName() {
		if(admin) {
			return "超级管理员"; 
		}else if(roles.size() == 0) {
			return "NO ROLES";
		}else {
			StringBuilder sb = new StringBuilder(30);
			sb.append("[");
			int i = 0;
			for (Role role : roles) {
				sb.append(role.getName());
				i++;
				if(i < roles.size()) {
					sb.append(",");
				}
			}
			sb.append("]");
			return sb.toString();
		}
	}
}

package com.fjw.domain;

import java.util.HashSet;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class SystemMenu {
	private Long id;
	private String name;
	private String url;
	private String sn;
	private SystemMenu parent;//多对一
	private Set<SystemMenu> children = new HashSet<>();//一对多
	private Set<Role> roles = new HashSet<>();//多对多
}

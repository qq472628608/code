package com.fjw.domain;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter
public class Depot {
	private Long id;
	private String location;
	private String name;
	@Override
	public String toString() {
		return "Depot [id=" + id + ", location=" + location + ", name=" + name + "]";
	}
	
}

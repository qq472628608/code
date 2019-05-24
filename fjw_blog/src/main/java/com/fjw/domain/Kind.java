package com.fjw.domain;

import org.apache.ibatis.type.Alias;

import lombok.Getter;
import lombok.Setter;

@Setter@Getter@Alias("Kind")
public class Kind {
	private Long id;
	private String name;
	private Integer sn;
	@Override
	public String toString() {
		return "Kind [id=" + id + ", name=" + name + ", sn=" + sn + "]";
	}
	
}

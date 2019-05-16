package com.fjw.generator;

import lombok.Getter;

@Getter
public class ClassInfo {
	private String className;
	private String pkg;
	
	public ClassInfo(Class<?> clz){
		this.className = clz.getSimpleName();
		String packageName = clz.getPackage().getName();
		this.pkg = packageName.substring(0, packageName.lastIndexOf("."));
	}
}

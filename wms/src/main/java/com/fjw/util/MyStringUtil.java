package com.fjw.util;

public class MyStringUtil {
	public static boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
}

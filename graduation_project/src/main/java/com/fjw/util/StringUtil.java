package com.fjw.util;

import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;

import org.apache.logging.log4j.core.util.UuidUtil;

public class StringUtil {
	
	public static boolean hasLength(String str) {
		return str != null && !"".equals(str.trim());
	}
	
	public static boolean chekMail(String str) {
		return hasLength(str) && str.length() >= 12 && str.length() <= 20 && str.matches("^\\s*\\w+(?:\\.{0,1}[\\w-]+)*@[a-zA-Z0-9]+(?:[-.][a-zA-Z0-9]+)*\\.[a-zA-Z]+\\s*$");
	}
	
	public static boolean checkPhone(String str) {
		return hasLength(str) && str.length() == 11 && str.matches("^((13[0-9])|(14[5|7])|(15([0-3]|[5-9]))|(17[013678])|(18[0,5-9]))\\d{8}$");
	}
	
	public static boolean checkPassword(String str) {
		return hasLength(str) && str.length() >= 8 && str.length() <= 16 && str.matches("[a-zA-Z]{1}([a-zA-Z0-9]){7,17}");
	}
	
	public static String getOrderCode() {
		Date date = new Date();
		StringBuilder builder = new StringBuilder();
		builder.append(date.getDay());
		builder.append(date.getHours());
		builder.append(date.getMonth());
		builder.append(date.getSeconds());
		builder.append(date.getMinutes());
		builder.append((""+date.getYear()).substring(1, 3));
		builder.append(Math.abs(UUID.randomUUID().toString().hashCode()));
		return builder.toString();
	} 
	
	public static String getFormatDate(Date date) {
		if(date != null) {
			SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
			return format.format(date).toString();
		}
		return null;
	}
	
	public static String getUploadPath() {
		return System.getProperty("user.dir")+"/upload/";
	}
}

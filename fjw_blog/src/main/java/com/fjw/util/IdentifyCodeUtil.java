package com.fjw.util;

import java.util.Random;

public class IdentifyCodeUtil {
	public static String getIndetifyCode() {
		String code = "0123456789abcdefghijklmnopqrstuvwxyz";
		char[] ch = new char[6];
		for (int i = 0; i < 6; i++) {
			Random random = new Random();
			int index = random.nextInt(code.length());
			ch[i] = code.charAt(index);
		}
		return String.valueOf(ch);
	}
}

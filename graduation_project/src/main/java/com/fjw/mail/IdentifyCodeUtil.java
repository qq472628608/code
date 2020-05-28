package com.fjw.mail;

import java.util.Random;

public class IdentifyCodeUtil {
	public static String getIdentifyCode() {
		String str = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";
		StringBuffer buf = new StringBuffer();
		Random random = new Random();
		for (int i = 0; i < 6; i++) {
			buf.append(str.charAt(random.nextInt(str.length())));
		}
		return buf.toString();
	}
}

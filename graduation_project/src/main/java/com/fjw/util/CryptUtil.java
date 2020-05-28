package com.fjw.util;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

public class CryptUtil {
	private static BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
	
	public static String encryption(String password) {
		return bCryptPasswordEncoder.encode(password);
	}
	
	public static boolean mathchs(String password,String encodePassword){
		return bCryptPasswordEncoder.matches(password, encodePassword);
	}

}

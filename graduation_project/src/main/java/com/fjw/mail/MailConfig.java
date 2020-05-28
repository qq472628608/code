package com.fjw.mail;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class MailConfig {
	private static final String DEFAULT_PROPERTIES = "mailConfig.properties";
	public static String host;
	public static Integer port;
	public static String username;
	public static String password;
	public static String emailFrom;
	public static String timeout;
	public static Properties properties;
	static {
		init();
	}
	private static void init() {
		properties = new Properties();
		InputStream stream = MailConfig.class.getClassLoader().getResourceAsStream(DEFAULT_PROPERTIES);
		try {
			properties.load(stream);
			stream.close();
			host = properties.getProperty("mailHost");
			port = Integer.parseInt(properties.getProperty("mailport"));
			username = properties.getProperty("mailUsername");
			password = properties.getProperty("mailPassword");
			emailFrom = properties.getProperty("mailFrom");
			timeout = properties.getProperty("mailTimeout");
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}

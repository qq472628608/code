package com.fjw.mail;

import java.util.Properties;

import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;


public class MailUtil {
	private static final String HOST = MailConfig.host;
	private static final Integer PORT = MailConfig.port;
	private static final String USERNAME = MailConfig.username;
	private static final String PASSWORD = MailConfig.password;
	private static final String EMAILFROM = MailConfig.emailFrom;
	private static final String TIMEOUT = MailConfig.timeout;
	private static JavaMailSenderImpl sender = createSender();
	
	private static JavaMailSenderImpl createSender() {
		 JavaMailSenderImpl sender = new JavaMailSenderImpl();
		 sender.setHost(HOST);
		 sender.setPort(PORT);
		 sender.setUsername(USERNAME);
		 sender.setPassword(PASSWORD);
		 sender.setDefaultEncoding("UTF-8");
		 Properties properties = new Properties();
		 properties.setProperty("mail.smtp.timeout", TIMEOUT);
		 properties.setProperty("mail.smtp.auth", "false");
		 sender.setJavaMailProperties(properties);
		 return sender;
	}
	
	public static void sendMail(String target,String subject,String text) {
		try {
		SimpleMailMessage msg = new SimpleMailMessage();
		msg.setFrom(EMAILFROM);
		msg.setTo(target);
		msg.setSubject(subject);
		msg.setText(text);
			sender.send(msg);			
		} catch (MailException e) {
			e.printStackTrace();
		}
	}
}

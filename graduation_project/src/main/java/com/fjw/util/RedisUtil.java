package com.fjw.util;

import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import com.fjw.cache.FastJsonRedisSerializer;

public class RedisUtil {
	
	private static RedisConnection connection;
	private static FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
	
	public static void getConnection() {
		LettuceConnectionFactory factory = ApplicationContextHolder.getBean("lettuceConnectionFactory");
		RedisConnection connection = factory.getConnection();
		RedisUtil.connection = connection;
	}
	public static void set(String key,Object value) {
		if(connection == null) {
			getConnection();
		}
		connection.set(key.getBytes(), fastJsonRedisSerializer.serialize(value));
		connection.expire(key.getBytes(), 60*60*24*7);
	}
	
	public static Object get(String key) {
		if(connection == null) {
			getConnection();
		}
		return fastJsonRedisSerializer.deserialize(connection.get(key.getBytes()));
	}
	
	public static void delete(String key) {
		if(connection == null) {
			getConnection();
		}
		connection.expire(key.getBytes(), 0);
	}
}

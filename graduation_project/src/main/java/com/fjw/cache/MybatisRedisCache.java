/*package com.fjw.cache;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.ibatis.cache.Cache;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory;

import com.fasterxml.jackson.databind.ser.std.StringSerializer;
import com.fjw.util.ApplicationContextHolder;

public class MybatisRedisCache implements Cache{
	
	private final ReadWriteLock lock = new ReentrantReadWriteLock();
	private String id;
	private FastJsonRedisSerializer<Object> fastJsonRedisSerializer = new FastJsonRedisSerializer<>(Object.class);
	private StringSerializer stringSerializer = new StringSerializer();
	private RedisConnection connection;
	
	*//**
	 * provide constructer must hava a param id
	 * @param id
	 *//*
	public MybatisRedisCache(String id) {
		if(id == null) {
			throw new IllegalArgumentException("MybatisRedisCache need an id");
		}
		this.id = id;
	}
	
	*//**
	 * return id
	 *//*
	public String getId() {
		return id;
	}

	*//**
	 * put key value to redis
	 *//*
	public void putObject(Object key, Object value){
		if(redisTemplate != null) {
			System.out.println(key.toString());
			redisTemplate.execute((RedisCallback)redis -> {redis.select(0); return null;});
			redisTemplate.opsForValue().set(key.toString(), value, 1l, TimeUnit.DAYS);
		}
		if(this.connection == null) {
			getConnect();
		}
		connection.set(key.toString().getBytes(), fastJsonRedisSerializer.serialize(value));
		connection.expire(key.toString().getBytes(), 60*60*24*7);
	}

	*//**
	 * get value of redis by key
	 *//*
	public Object getObject(Object key) {
		if(this.connection == null) {
			getConnect();
		}
		return fastJsonRedisSerializer.deserialize(connection.get(key.toString().getBytes()));
		if(redisTemplate != null) {
			redisTemplate.execute((RedisCallback)redis -> {redis.select(0); return null;});
			return redisTemplate.opsForValue().get(key.toString());
		}
		return null;
	}

	*//**
	 * remove value of redis by key
	 *//*
	public Object removeObject(Object key) {
		if(this.connection == null) {
			getConnect();
		}
		return connection.expire(key.toString().getBytes(), 0);
		if(redisTemplate != null) {
			redisTemplate.execute((RedisCallback)redis -> {redis.select(0); return null;});
			return redisTemplate.delete(key.toString());
		}
		return null;
	}

	*//**
	 * clear redis 
	 *//*
	public void clear() {
		if(this.connection == null) {
			getConnect();
		}
		connection.flushDb();
		if(redisTemplate != null) {
			redisTemplate.execute((RedisCallback)redis -> {redis.select(0); return null;});
			redisTemplate.execute((RedisCallback)redis -> {redis.flushDb(); return null;});
		}
	}

	*//**
	 * get the size of redis
	 *//*
	public int getSize() {
		if(redisTemplate != null) {
			redisTemplate.execute((RedisCallback)redis -> {redis.select(0); return null;});
			return (int)redisTemplate.execute((RedisCallback)redis -> {return redis.dbSize().intValue();});
		}
		return 0;
		if(this.connection == null) {
			getConnect();
		}
		return Integer.parseInt(connection.dbSize().toString());
	}

	public ReadWriteLock getReadWriteLock() {
		return this.lock;
	}
	
	public void getConnect() {
		LettuceConnectionFactory factory = ApplicationContextHolder.getBean("lettuceConnectionFactory");
		RedisConnection connection = factory.getConnection();
		connection.select(0);
		this.connection = connection;
	}
}
*/
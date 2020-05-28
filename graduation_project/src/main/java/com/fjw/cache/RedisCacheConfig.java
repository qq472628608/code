 package com.fjw.cache;

import java.time.Duration;

import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.fastjson.parser.ParserConfig;

@Configuration
public class RedisCacheConfig extends CachingConfigurerSupport{
	 	@Bean
	    public KeyGenerator keyGenerator(){
	        return (o, method, params) ->{
	            StringBuilder sb = new StringBuilder();
	            sb.append(o.getClass().getName()); // 类目
	            sb.append(method.getName()); // 方法名
	            for(Object param: params){
	            	if(param != null) {
	            		sb.append(param.toString()); // 参数名
	            	}
	            }
	            return sb.toString();
	        };
	    }

	    // 配置缓存管理器
	    @Bean
	    public RedisCacheManager cacheManager(RedisConnectionFactory connectionFactory) {
	        RedisCacheConfiguration config = RedisCacheConfiguration.defaultCacheConfig()
	                .entryTtl(Duration.ofSeconds(1800)) // 60s缓存失效
	                // 设置key的序列化方式
	                .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(keySerializer()))
	                // 设置value的序列化方式
	                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(valueSerializer()));
	                // 不缓存null值
	                //.disableCachingNullValues();

	        RedisCacheManager redisCacheManager = RedisCacheManager.builder(connectionFactory)
	                .cacheDefaults(config)
	                .transactionAware()
	                .build();

	        return redisCacheManager;
	    }

	    @Bean(name="redisTemplate")
	    public RedisTemplate<Object, Object> redisTemplate(RedisConnectionFactory connectionFactory){
	        RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<>();
	        redisTemplate.setConnectionFactory(connectionFactory);
	        redisTemplate.setKeySerializer(keySerializer());
	        redisTemplate.setHashKeySerializer(keySerializer());
	        redisTemplate.setValueSerializer(valueSerializer());
	        redisTemplate.setHashValueSerializer(valueSerializer());
	        return redisTemplate;
	    }

	    // key键序列化方式
	    private RedisSerializer<String> keySerializer() {
	        return new StringRedisSerializer();
	    }

	    // value值序列化方式
	    private FastJsonRedisSerializer<Object> valueSerializer(){
	    	ParserConfig.getGlobalInstance().setAutoTypeSupport(true); 
	        return new FastJsonRedisSerializer<>(Object.class);
	    }
}

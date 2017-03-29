package com.telincn.config.redis;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.jedis.JedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.StringRedisSerializer;

import com.alibaba.druid.util.StringUtils;

import redis.clients.jedis.JedisPoolConfig;  

@Configuration  
@EnableConfigurationProperties({ RedisSettings.class })
@EnableCaching
public class RedisConfig{
	
	@Autowired
	private RedisSettings settings;
	
	@Bean(name = "jedisPoolConfig")
	public JedisPoolConfig jedisPoolConfig(){
		JedisPoolConfig jedisPoolConfig = new JedisPoolConfig();
		jedisPoolConfig.setMaxTotal(settings.getMaxTotal());
		jedisPoolConfig.setMaxIdle(settings.getMaxIdle());
		jedisPoolConfig.setMaxWaitMillis(settings.getMaxWaitMillis());
		jedisPoolConfig.setTestOnBorrow(settings.getTestOnBorrow());
		
		return jedisPoolConfig;
	}
	
	@Bean(name = "jedisFactory")
	public JedisConnectionFactory jedisConnectionFactory(@Qualifier("jedisPoolConfig") JedisPoolConfig jedisPoolConfig){
		JedisConnectionFactory jedisConnectionFactory = new JedisConnectionFactory();
		jedisConnectionFactory.setHostName(settings.getHost());
		jedisConnectionFactory.setPort(settings.getPort());
		if(!StringUtils.isEmpty(settings.getPassword())){
			jedisConnectionFactory.setPassword(settings.getPassword());
		}
		jedisConnectionFactory.setUsePool(true);
		jedisConnectionFactory.setPoolConfig(jedisPoolConfig);
		jedisConnectionFactory.setDatabase(Integer.parseInt(settings.getDatabase()));
		return jedisConnectionFactory;
	}	
	
	@SuppressWarnings("rawtypes")
	@Bean(name = "redisTemplate")
	public RedisTemplate redisTemplate(@Qualifier("jedisFactory") JedisConnectionFactory jedisFactory){
		RedisTemplate<Object, Object> redisTemplate = new RedisTemplate<Object, Object>();
		redisTemplate.setConnectionFactory(jedisFactory);
		// 使用String格式序列化缓存键
        StringRedisSerializer stringRedisSerializer = new StringRedisSerializer();
        redisTemplate.setKeySerializer(stringRedisSerializer);
        redisTemplate.setHashKeySerializer(stringRedisSerializer);
        
        redisTemplate.setValueSerializer(stringRedisSerializer);
        redisTemplate.setHashValueSerializer(stringRedisSerializer);
        
		return redisTemplate;
	}
	
}  
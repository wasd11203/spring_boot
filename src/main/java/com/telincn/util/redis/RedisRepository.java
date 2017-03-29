package com.telincn.util.redis;

import java.util.concurrent.TimeUnit;

import javax.annotation.PostConstruct;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Repository;

/**
 * jedis 操作redis 方式:
 *  1. 可使用jedisPool 连接池获取连接,但是要手动释放连接
 *  2. 可使用redisTemplate手动操作redis
 * @author ganzhigang
 * 时间：2017年3月29日 下午2:40:55
 */
@Repository("redisRepository")
public class RedisRepository {

	private Logger logger = LoggerFactory.getLogger(RedisRepository.class);
	
	@Autowired
	private RedisTemplate<Object, Object> redisTemplate;
	
	private ValueOperations<Object, Object> valueOperations;

	@PostConstruct
	private void init() {
		// 序列化操作,在序列化String时会自动加\" 故注释
		// template.setValueSerializer(new JacksonJsonRedisSerializer<>(String.class));
		valueOperations = redisTemplate.opsForValue();
	}
	
	/**
	 * 判断 redis 中是否存在指定的 key
	 * @param key
	 * @return
	 */
	public boolean hasKey(Object key){
		return redisTemplate.hasKey(key);
	}
	
	
	/**
	 * 将传入的key--value 的 存入/更新 redis,并且时间是永不过期.
	 * @param key
	 * @param value
	 */
	public void saveToRedis(Object key,Object value){
		
		if( !redisTemplate.hasKey(key) ){
			logger.debug("redis 中 新增 key为: "+key +", value为: "+value +" 的数据");
			valueOperations.set(key, value);
		}else{
			Object valueTmp = valueOperations.get(key) ;
			logger.debug("redis 中 修改 key为: "+key +" 的数据,"+"旧的value为: "+valueTmp+", 新的value为: "+value +" 的数据");
			valueOperations.set(key, value);
		}
	}
	
	/**
	 * 将传入的key--value 的 存入/更新 redis,并且指定有效期是 以 传入的unit 为单位的 时间长度
	 * @param key
	 * @param value
	 * @param timeOut
	 * @param unit
	 */
	public void saveToRedis(Object key,Object value,Long timeOut,TimeUnit unit){
		
		if( !redisTemplate.hasKey(key) ){
			logger.debug("redis 中 新增 key为: "+key +", value为: "+value +" 的数据");
			valueOperations.set(key, value);
		}else{
			Object valueTmp = valueOperations.get(key) ;
			logger.debug("redis 中 修改 key为: "+key +" 的数据,"+"旧的value为: "+valueTmp+", 新的value为: "+value +" 的数据");
			valueOperations.set(key, value);
		}
		
		
		redisTemplate.expire(key, timeOut, unit);
	}
	
	
	/**
	 * 将redis 中 指定的key 删除
	 * @param key
	 */
	public void delFromRedis(Object key){
		
		if( !redisTemplate.hasKey(key) ){
			logger.debug("redis 中不存在 key为: "+key +" 的数据");
			return ;
		}else{
			Object valueTmp = valueOperations.get(key) ;
			logger.debug("删除 redis 中  key为: "+key +" ,value为: "+valueTmp+" 的数据");
			redisTemplate.delete(key);
		}
		
	}
	
	/**
	 * 从redis 中获取指定key的value
	 * @param key
	 * @return
	 */
	public Object getFromRedis(Object key){
		
		if( !redisTemplate.hasKey(key) ){
			logger.debug("redis 中不存在 key为: "+key +" 的数据");
			return null;
		}else{
			Object valueTmp = valueOperations.get(key) ;
			logger.debug("获取 redis 中  key为: "+key +" ,value为: "+valueTmp+" 的数据");
			return valueTmp;
		}
	}
	
}

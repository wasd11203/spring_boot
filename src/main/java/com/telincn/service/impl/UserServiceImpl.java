package com.telincn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import com.telincn.mapper.UserMapper;
import com.telincn.service.UserService;
import com.telincn.util.redis.RedisRepository;

/**
 * @Cacheable	表明Spring在调用方法之前，首先应该在缓存中查找方法的返回值，如果这个值能够找到，就会返回缓存的值。否则，这个方法就会被调用，返回值会放到缓存之中
 * @CachePut	表名Spring应该将方法的返回值放到缓存中。在方法的调用前并不会检查缓存，方法始终都会被调用
 * @CacheEvict	表明Spring应该在缓存中清除一个或多个条目
 * @Caching	这是一个分组的注解，能够同时应用多个其他的缓存注解
 * @CacheEvict能够应用在返回值为void的方法上， 而@Cacheable和@CachePut需要非void的返回值，他将会作为放在缓存中的条目
 */

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
	private RedisRepository redisRepository;
	
	/**
	 * Cacheable 支持如下几个参数：
	 * 	value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name
	 * 	key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL
	 * 	condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
	 */
	@Cacheable(value="demoInfo") //缓存到redis中,这里没有指定key.
	@Override
	public String saveToRedis(String username) {
		redisRepository.saveToRedis("a","b");
		return userMapper.selectUserByName(username).getUsername();
	}
	
	/**
	 * @CacheEvict 支持如下几个参数：
	 * value：缓存位置名称，不能为空，同上
	 * key：缓存的key，默认为空，同上
	 * condition：触发条件，只有满足条件的情况才会清除缓存，默认为空，支持SpEL
	 * allEntries：true表示清除value中的全部缓存，默认为false
	 */
	@CacheEvict(value="demoInfo") // 
	public String delFromRedis(String username) {
		 System.out.println("UserServiceImpl.delFromCache().从缓存中删除.");
		return userMapper.selectUserByName(username).getUsername();
	}

}

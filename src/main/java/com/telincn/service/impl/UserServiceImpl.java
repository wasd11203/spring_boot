package com.telincn.service.impl;

import java.util.concurrent.TimeUnit;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

import com.telincn.entity.User;
import com.telincn.mapper.UserMapper;
import com.telincn.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Autowired
    private RedisTemplate<String,String> redisTemplate;
	
	@Override
	public void test() {
		  ValueOperations<String,String> valueOperations = redisTemplate.opsForValue();
		  // 新增 缓存数据
          valueOperations.set("mykey4", "random1="+Math.random());
          // 获取 缓存数据
          System.out.println(valueOperations.get("mykey4"));
          // 删除 缓存数据
//          redisTemplate.delete("mykey4");
          // 设置 指定key的 有效时间
          redisTemplate.expire("mykey4", 60L, TimeUnit.SECONDS);
	}
	
	/**
	 * Cacheable 支持如下几个参数：
	 * 	value：缓存位置名称，不能为空，如果使用EHCache，就是ehcache.xml中声明的cache的name
	 * 	key：缓存的key，默认为空，既表示使用方法的参数类型及参数值作为key，支持SpEL
	 * 	condition：触发条件，只有满足条件的情况才会加入缓存，默认为空，既表示全部都加入缓存，支持SpEL
	 */
	@Cacheable(value="demoInfo") //缓存到redis中,这里没有指定key.
	@Override
	public String saveToRedis(String username) {
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
	public String delFromCache(String username) {
		 System.out.println("UserServiceImpl.delFromCache().从缓存中删除.");
		return userMapper.selectUserByName(username).getUsername();
	}

}

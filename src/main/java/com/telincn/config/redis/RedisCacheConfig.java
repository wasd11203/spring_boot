package com.telincn.config.redis;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.cache.RedisCacheManager;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;

/**
 * redis 缓存配置; 注意：
 * RedisCacheConfig这里也可以不用继承：CachingConfigurerSupport，也就是直接一个普通的Class就好了；
 * 这里主要我们之后要重新实现key的生成策略，只要这里修改KeyGenerator，其它位置不用修改就生效了。
 * 普通使用普通类的方式的话，那么在使用@Cacheable的时候还需要指定KeyGenerator的名称;这样编码的时候比较麻烦。
 * 
 * @author ganzhigang 时间：2017年3月28日 上午9:51:44
 */
@Configuration
@EnableCaching // 启用缓存，这个注解很重要；
public class RedisCacheConfig extends CachingConfigurerSupport {

	/**
	 * 自定义 key生成策略：
	 * 此方法将会根据类名+方法名+所有参数的值生成唯一的一个key,即使@Cacheable中的value属性一样，key也会不一样。
	 */
	@Override
	public KeyGenerator keyGenerator() {

		System.out.println("RedisCacheConfig.keyGenerator()");

		return new KeyGenerator() {

			@Override
			public Object generate(Object o, Method method, Object... objects) {

				// This willgenerate a unique key of the class name, the method
				// name
				// and allmethod parameters appended.

				StringBuilder sb = new StringBuilder();
				sb.append(o.getClass().getName());
				sb.append(method.getName());

				for (Object obj : objects) {
					sb.append(obj.toString());
				}
				System.out.println("keyGenerator=" + sb.toString());

				return sb.toString();
			}
		};
	}

	/**
	 * 缓存管理器.
	 * 
	 * @param redisTemplate
	 * @return
	 */
	@Bean(name = "cacheManager")
	public CacheManager cacheManager(RedisTemplate<?, ?> redisTemplate) {
		
		RedisCacheManager cacheManager = new RedisCacheManager(redisTemplate);
		
		//设置缓存过期时间
        cacheManager.setDefaultExpiration(60);//秒
        //设置value的过期时间
        Map<String,Long> map=new HashMap<String, Long>();
        map.put("test",60L);
        cacheManager.setExpires(map);
		
		return cacheManager;
	}

	/**
	 * 
	 * redis模板操作类,类似于jdbcTemplate的一个类; 虽然CacheManager也能获取到Cache对象，但是操作起来没有那么灵活；
	 * 这里再扩展下： RedisTemplate这个类不见得很好操作，我们可以再进行扩展一个我们 自己的缓存类，比如：RedisStorage类;
	 * 
	 * @param factory:
	 *            通过Spring进行注入，参数在application.properties进行配置；
	 * @return
	 */
	@Bean(name = "redisTemplate")
	public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory factory) {

		RedisTemplate<String, String> redisTemplate = new RedisTemplate<String, String>();
		redisTemplate.setConnectionFactory(factory);
		
//		RedisTemplate默认定义了两个常用的序列化类:
//		1. private RedisSerializer<?> defaultSerializer = new JdkSerializationRedisSerializer();
//		2. private RedisSerializer<String> stringSerializer = new StringRedisSerializer(); === 只能将字符串 序列化到 redis
		
//		 key序列化方式（不然会出现乱码）：
//		 但是如果方法上有Long等非String类型的话，会报类型转换错误；
//		 所以在没有自己定义key生成策略的时候，以下这个代码建议不要这么写，可以不配置或者自己实现ObjectRedisSerializer或者JdkSerializationRedisSerializer序列化方式;
		 RedisSerializer<String> redisSerializer = new StringRedisSerializer();////Long类型会出现异常信息;需要我们上面的自定义key生成策略，一般没必要
		 redisTemplate.setKeySerializer(redisSerializer);
		 redisTemplate.setHashKeySerializer(redisSerializer);
//		 value序列化方式（不然会出现乱码）
		 redisTemplate.setValueSerializer(redisSerializer);
		 redisTemplate.setHashValueSerializer(redisSerializer);
		 
		return redisTemplate;

	}

}
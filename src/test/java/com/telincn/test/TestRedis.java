package com.telincn.test;

import java.util.concurrent.TimeUnit;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.telincn.WebApplicationConfiguration;
import com.telincn.entity.User;
import com.telincn.util.redis.RedisRepository;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = WebApplicationConfiguration.class)
@WebAppConfiguration
public class TestRedis {

	@Autowired
	private RedisRepository redisRepository;
	
	@Test
	public void test(){
		User user = new User();
		redisRepository.saveToRedis("a", "b");
		redisRepository.saveToRedis("a", "c", 60L, TimeUnit.SECONDS);
		redisRepository.saveToRedis("a", user.toString());
	}
	
	
}

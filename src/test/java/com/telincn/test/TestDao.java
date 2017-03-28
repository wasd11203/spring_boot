package com.telincn.test;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;

import com.telincn.WebApplicationConfiguration;
import com.telincn.service.UserService;

@RunWith(SpringJUnit4ClassRunner.class)  
@SpringApplicationConfiguration(classes = WebApplicationConfiguration.class)
@WebAppConfiguration
public class TestDao {

	@Autowired
	private UserService userService;
	
	@Test
	public void test(){
		System.out.println(userService.saveToRedis("aa"));
	}
	
	
}

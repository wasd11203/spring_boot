package com.telincn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telincn.service.UserService;

@RestController
public class HelloController {
	
	private Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/saveToredis")
	public String save (){
		
		String u = userService.saveToRedis("aa");
		logger.debug(u);
		
		return u;
	}
	
	@RequestMapping("/delFromredis")
	public String delFrom (){
		
		String u = userService.delFromRedis("aa");
		logger.debug(u);
		
		return "aa";
	}
	
	
	
}

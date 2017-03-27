package com.telincn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telincn.entity.User;
import com.telincn.service.UserService;

@RestController
public class HelloController {
	
	private Logger logger = LoggerFactory.getLogger(HelloController.class);
	
	@Autowired
	private UserService userService;
	
	@RequestMapping("/hello")
	public String hello (){
		
		User u = userService.selectUserByName("aa");
		logger.debug(u.getPassword());
		
		return "aa";
	}
	
	@RequestMapping("/index")
	public String index (){
		
		return "index";
	}
	
	@RequestMapping("/fn")
	public String fn (){
		return "fn";
	}
	
	@RequestMapping("/test")
	public String test (){
		
		User u = userService.selectUserByName("aa");
		logger.debug(u.getPassword());
		
		return "aaasdf";
	}

}

package com.telincn.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telincn.entity.User;
import com.telincn.mapper.UserMapper;

@RestController
public class HelloController {
	
	private Logger logger = LoggerFactory.getLogger(HelloController.class);
	@Autowired
	private UserMapper userMapper;
	
	@RequestMapping("/hello")
//	@PreAuthorize("hasRole('ROLE_ADMIN')")
	public String hello (){
		
		User u = userMapper.selectUserByName("aa");
		logger.debug(u.getPassword());
		
		return "aa";
	}
	
}

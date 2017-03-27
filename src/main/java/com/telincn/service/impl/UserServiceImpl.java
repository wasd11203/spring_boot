package com.telincn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telincn.entity.User;
import com.telincn.mapper.UserMapper;
import com.telincn.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{

	@Autowired
	private UserMapper userMapper;
	
	@Override
//	@PreAuthorize("hasRole('ROLE_USER')")
	public User selectUserByName(String username) {
		
		return userMapper.selectUserByName(username);
	}

}

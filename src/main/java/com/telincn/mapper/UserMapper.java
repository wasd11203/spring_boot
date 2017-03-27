package com.telincn.mapper;

import com.telincn.entity.User;

public interface UserMapper {
	
	public User selectUserByName(String username);
	
}

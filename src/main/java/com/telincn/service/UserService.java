package com.telincn.service;

import com.telincn.entity.User;

public interface UserService {
	public User selectUserByName(String username);
}

package com.telincn.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telincn.entity.User;
import com.telincn.mapper.UserDAO;
import com.telincn.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public User createUser(String username, String password) {
		String userId = null;
		userId = UUID.randomUUID().toString();
		User user = new User(userId, username, password);
		user = userDAO.save(user);
		return user;
	}

	@Override
	public User delUser(String userId) {

		userDAO.delete(userId);
		User user = new User();
		user.setId(userId);

		return user;

	}

	@Override
	public User updateUser(String userId, String username, String password) {

		User user = new User(userId, username, password);
		user = userDAO.save(user);

		return user;

	}

	@Override
	public List<User> getAllUserExceptCurrentUser(String userId) {

		List<User> list = userDAO.findAllExceptCurrentUser(userId);

		return list;

	}

	@Override
	public User findByUsername(String username, String password) {

		User user = userDAO.findByUsername(username);

		return user;
	}

	@Override
	public User findByUsername(String username) {

		User user = userDAO.findByUsername(username);

		return user;
	}

	@Override
	public User findByUserId(String userId) {

		User user = userDAO.findOne(userId);

		return user;
	}

	@Override
	public boolean existsUser(String userId) {
		boolean flag = userDAO.exists(userId);
		return flag;
	}

}

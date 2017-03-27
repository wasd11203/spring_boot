package com.telincn.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telincn.entity.ExEntity;
import com.telincn.repository.DAO;
import com.telincn.service.UserService;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private DAO dao;
	
	@Override
	public ExEntity findById(String demoId) {
		return dao.findById(Integer.parseInt(demoId));
	}

}

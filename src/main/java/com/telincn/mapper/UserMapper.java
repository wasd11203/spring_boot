package com.telincn.mapper;

import com.telincn.entity.User;

/**
 * 映射器 
 * @author ganzhigang
 * 时间：2017年3月28日 上午9:39:30
 */
public interface UserMapper {
	
	public User selectUserByName(String username);
	
}

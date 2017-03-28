package com.telincn.service;

import com.telincn.entity.User;

/**
 * 业务层
 * @author ganzhigang
 * 时间：2017年3月28日 上午9:39:44
 */
public interface UserService {
	
	public void test();
	
	public String saveToRedis(String username);
	
	public String delFromCache(String username);
}

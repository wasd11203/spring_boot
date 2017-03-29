package com.telincn.service;

/**
 * 业务层
 * @author ganzhigang
 * 时间：2017年3月28日 上午9:39:44
 */
public interface UserService {
	
	public String saveToRedis(String username);
	
	public String delFromRedis(String username);
}

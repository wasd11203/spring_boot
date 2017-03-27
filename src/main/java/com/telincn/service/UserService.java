package com.telincn.service;

import java.util.List;

import com.telincn.entity.User;

/**
 * 针对 用户 的业务层
 * @author 99
 *
 */
public interface UserService {
	/**
	 * 新增订单:
	 * 	新增用户名为传入的username参数,密码为传入的password参数的用户,并将新增的用户的信息封装为一个User对象返回
	 * @param username 新增的用户名
	 * @param password 新增的密码
	 * @return 封装了新增用户信息的User对象
	 */
	public User createUser(String username,String password);
	
	/**
	 * 删除指定用户:
	 * 	删除用户id为传入参数userId的订单记录,并将删除的用户信息封装为一个User对象返回
	 * @param userId 要删除的用户id
	 * @return 封装了被删除的用户信息的User对象
	 */
	public User delUser(String userId);
	
	/**
	 * 更新指定的用户:
	 * 	根据传入的userId的值查询到记录,然后将username,password 的值更新到记录中,将最新的指定的用户信息封装为User对象返回
	 * @param userId 要更新的用户id
	 * @param username 最新的用户名
	 * @param password 最新的密码
	 * @return 封装了指定的用户最新信息的User对象
	 */
	public User updateUser(String userId,String username,String password);
	
	public boolean existsUser(String userId);
	
	/**
	 * 查询除了当前账户的所有用户:
	 * 	根据传入的当前用户的userId,查询除了当前账户外的所有用户,并将结果集封装为List集合返回
	 * @param userId 要排除的用户id
	 * @param request 当前请求
	 * @return 封装了除当前账号外的所有用户信息的List集合
	 */
	public List<User> getAllUserExceptCurrentUser(String userId);
	
	/**
	 * 根据用户名查询指定的记录:
	 * 	根据传入的username参数的值查询指定的用户记录,并将password与查询到的记录中的password比对,返回比对结果
	 * 	1. 如果一致,则将该记录封装为User对象返回
	 * 	2. 否则,抛出异常
	 * @param username 要查询的用户名
	 * @param password 输入的密码(用于与DB中的密码比对)
	 * @return 一个封装了当前用户信息的User对象
	 */
	public User findByUsername(String username , String password );
	
	/**
	 * 根据用户名查询指定的记录:
	 * 	根据传入的username参数的值查询指定的用户记录,并将记录返回
	 * @param username 要查询的用户的用户名
	 * @return User
	 * @author 99
	 * @Date 2016年11月28日
	 */
	public User findByUsername(String username);
	
	/**
	 * 根据用户id查询指定的记录:
	 * 	根据传入的userId参数的值,查询指定的用户记录,并将得到的用户信息封装为一个User对象返回
	 * @param userId 要查询的用户id
	 * @param request 当前请求
	 * @return 封装了用户信息的User对象
	 */
	public User findByUserId(String userId);
	
	
}

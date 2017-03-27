package com.telincn.service;

import java.util.List;

import com.telincn.entity.Order;

/**
 * 针对 订单 的业务层接口
 * @author 99
 *
 */
public interface OrderService {
	
	/**
	 * 新增订单:
	 * 	新增订单名为传入的参数的订单
	 * @param ordername 新增的订单名
	 * @return 新增的订单对象
	 */
	public Order createOrder(String ordername);
	
	/**
	 * 删除订单:
	 * 	删除订单id为传入的参数的订单
	 * @param orderId 要删除的订单id
	 * @return 删除的订单对象(只携带了已删除的订单的id)
	 */
	public Order delOrder(String orderId);
	
	/**
	 * 更新指定的订单信息:
	 * 	根据传入的订单id,查询订单,并将传入的参数,更新到DB中对应订单id的记录中,返回指定的最新的订单信息
	 * @param orderId 要更新的订单id
	 * @param ordername 最新的订单名
	 * @return 指定的最新的订单信息的订单对象
	 */
	public Order updateOrder(String orderId,String ordername);
	
	/**
	 * 查询所有的订单信息:
	 * 	查询所有的订单信息,并将结果集封装为List集合返回
	 * @return 查询到的所有订单的List集合
	 */
	public List<Order> getAllOrder();
	
	public boolean existsOrder(String orderId);
	
}

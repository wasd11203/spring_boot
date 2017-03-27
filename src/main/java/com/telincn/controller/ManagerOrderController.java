package com.telincn.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.telincn.entity.Order;
import com.telincn.service.OrderService;
import com.telincn.util.JsonResult;

/**
 * 针对 订单 的请求处理类
 * 
 * @author 99
 *
 */
@RestController
@RequestMapping("/order_manager")
public class ManagerOrderController {

	@Autowired
	private OrderService orderServiceImpl;

	/**
	 * 处理 查询所有订单 请求: 
	 * 	查询DB,将返回的数据返回给前端
	 * 
	 * @return Json类型的数据
	 */
	@RequestMapping("/listOrders.do")
	public JsonResult<List<Order>> listOrders() {

		JsonResult<List<Order>> result;
		
		List<Order> list = orderServiceImpl.getAllOrder();

//		if (null == list || list.isEmpty()) {
//			result = new JsonResult<List<Order>>("无订单");
//			return result;
//		}
		
		result = new JsonResult<List<Order>>(list);

		return result;
	}

	/**
	 * 处理 更新指定订单 请求: 
	 * 	根据请求携带的参数,查询DB并更新指定的记录,返回最新的指定订单信息并将其返回给前端
	 * 
	 * @param orderId
	 *            要更新的订单的id
	 * @param ordername
	 *            最新的订单的名称
	 * @return Json类型数据
	 */
	@RequestMapping("/updateOrder.do")
	public JsonResult<Order> updateOrder(String orderId, String ordername) {

		JsonResult<Order> result;

		if (null == orderId || orderId.trim().isEmpty()) {
			result = new JsonResult<Order>("订单id不能为空");
			return result;
		}
		if (null == ordername || ordername.trim().isEmpty()) {
			result = new JsonResult<Order>("订单名不能为空");
			return result;
		}
		if(!orderServiceImpl.existsOrder(orderId)){
			result = new JsonResult<Order>("订单不存在,请刷新后重试");
		}
		
		Order order = orderServiceImpl.updateOrder(orderId, ordername);
		result = new JsonResult<Order>(order);

		return result;
	}

	/**
	 * 处理 删除指定订单 请求: 
	 * 	根据请求携带的参数,查询DB并删除,返回指定删除的订单信息并将其返回给前端
	 * 
	 * @param orderId
	 *            要删除的订单id
	 * @return Json类型数据
	 */
	@RequestMapping("/deleteOrder.do")
	public JsonResult<Order> deleteOrder(String orderId) {

		JsonResult<Order> result;
		
		if (null == orderId || orderId.trim().isEmpty()) {
			result = new JsonResult<Order>("订单id不能为空");
			return result;
		}
		if(!orderServiceImpl.existsOrder(orderId)){
			result = new JsonResult<Order>("订单不存在,请刷新后重试");
		}
		
		Order order = orderServiceImpl.delOrder(orderId);
		result = new JsonResult<Order>(order);

		return result;
	}

	/**
	 * 处理 新建订单 请求: 
	 * 	根据请求携带的参数,新建一条订单记录,插入到DB中,返回新增的订单信息并返回给前端
	 * 
	 * @param ordername
	 *            新增的订单名
	 * @return Json类型数据
	 */
	@RequestMapping("/createOrder.do")
	public JsonResult<Order> createOrder(String ordername) {

		JsonResult<Order> result;

		if (null == ordername || ordername.trim().isEmpty()) {
			result = new JsonResult<Order>("订单名不能为空");
			return result;
		}

		Order order = orderServiceImpl.createOrder(ordername);
		result = new JsonResult<Order>(order);

		return result;
	}
}
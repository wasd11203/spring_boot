package com.telincn.service.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.telincn.entity.Order;
import com.telincn.mapper.OrderDAO;
import com.telincn.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {

	@Autowired
	private OrderDAO orderDAO;

	@Override
	public Order createOrder(String ordername) {
		String orderId = null;
		orderId = UUID.randomUUID().toString();
		Order order = new Order(orderId, ordername);
		order = orderDAO.save(order);

		return order;

	}

	@Override
	public Order delOrder(String orderId) {

		orderDAO.delete(orderId);

		Order order = new Order();
		order.setId(orderId);
		return order;

	}

	@Override
	public Order updateOrder(String orderId, String ordername) {

		Order order = new Order(orderId, ordername);
		order = orderDAO.save(order);

		return order;

	}

	@Override
	public List<Order> getAllOrder() {

		List<Order> list = (List<Order>) orderDAO.findAll();

		return list;

	}

	@Override
	public boolean existsOrder(String orderId) {
		return orderDAO.exists(orderId);
	}
	
}

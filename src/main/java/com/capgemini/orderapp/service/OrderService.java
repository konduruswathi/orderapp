package com.capgemini.orderapp.service;

import java.util.List;

import com.capgemini.orderapp.entities.Order;

public interface OrderService {
	public Order addOrder(Order order);

	public Order getOrderById(int orderId);

	public List<Order> findOrderByCustomerId(int customerId);

	public void deleteOrder(Order order);

	public List<Order> getAllOrders();
}

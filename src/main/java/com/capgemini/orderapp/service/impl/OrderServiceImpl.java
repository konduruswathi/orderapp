package com.capgemini.orderapp.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.mvc.condition.ProducesRequestCondition;

import com.capgemini.orderapp.entities.Order;
import com.capgemini.orderapp.exception.OrderAlreadyExistsException;
import com.capgemini.orderapp.exception.OrderNotFoundException;
import com.capgemini.orderapp.repository.OrderRepository;
import com.capgemini.orderapp.service.OrderService;

@Service
public class OrderServiceImpl implements OrderService {
	@Autowired
	private OrderRepository orderRepository;

	@Override
	public Order addOrder(Order order) throws OrderAlreadyExistsException {
		Optional<Order> orderFromDb = orderRepository.findById(order.getOrderId());
		if (orderFromDb.isPresent()) {
			return orderRepository.save(order);
		}

		throw new OrderAlreadyExistsException("order already placed");
	}

	@Override
	public Order getOrderById(int orderId) throws OrderNotFoundException {
		Optional<Order> optionalOrder = orderRepository.findById(orderId);
		if (optionalOrder.isPresent()) {
			return optionalOrder.get();
		}
		throw new OrderNotFoundException("order does not exist");
	}

	@Override
	public List<Order> getAllOrders() {

		return orderRepository.findAll();
	}

	@Override
	public void deleteOrder(Order order) {
		Optional<Order> optionalOrder1 = orderRepository.findById(order.getOrderId());
		if (optionalOrder1.isPresent()) {
			orderRepository.delete(order);
		}
		throw new OrderNotFoundException("order does not exist");
	}

	@Override
	public List<Order> findOrderByCustomerId(int customerId) {

		return orderRepository.findOrderByCustomerId(customerId);
	}
}

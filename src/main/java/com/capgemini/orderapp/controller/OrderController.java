package com.capgemini.orderapp.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.orderapp.entities.Order;
import com.capgemini.orderapp.service.OrderService;

@RestController
public class OrderController {

	@Autowired
	private OrderService orderService;

	@PostMapping("/order")
	public ResponseEntity<Order> addOrder(@RequestBody Order order) {

		return new ResponseEntity<Order>(orderService.addOrder(order), HttpStatus.OK);

	}

	@GetMapping("/order/{orderId}")
	public ResponseEntity<Order> findOrderById(@PathVariable int orderId) {
		return new ResponseEntity<Order>(orderService.getOrderById(orderId), HttpStatus.OK);
	}

	@GetMapping("/orders/{orderId}")
	public ResponseEntity<List<Order>> findOrderByCustomerId(@PathVariable int customerId) {
		return new ResponseEntity<List<Order>>(orderService.findOrderByCustomerId(customerId), HttpStatus.OK);
	}

	@GetMapping("/orders")
	public ResponseEntity<List<Order>> findAllOrders(@RequestBody Order order) {
		return new ResponseEntity<List<Order>>(orderService.getAllOrders(), HttpStatus.OK);

	}
	@DeleteMapping("/orders")
	public ResponseEntity<Order> deleteOrder(@PathVariable  int orderId){
		Order order=orderService.getOrderById(orderId);
	orderService.deleteOrder(order);
	return new ResponseEntity<Order>(HttpStatus.OK);
	}
	

}

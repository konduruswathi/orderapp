package com.capgemini.orderapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.JpaRepositoryConfigExtension;

import com.capgemini.orderapp.entities.Order;

public interface OrderRepository extends JpaRepository<Order, Integer>{

	
	@Query("{'customerId':?0}")
public List<Order> findOrderByCustomerId(int customerId);
}

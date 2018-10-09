package com.capgemini.orderapp;

import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.capgemini.orderapp.controller.OrderController;
import com.capgemini.orderapp.entities.Order;
import com.capgemini.orderapp.service.OrderService;

@RunWith(MockitoJUnitRunner.class)
@SpringBootTest
public class OrderControllerTest {
	@Mock
	private OrderService orderService;
	@InjectMocks
	private OrderController orderController;
	private MockMvc mockMvc;
	Order order;

	@Before
	public void setUp() {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(orderController).build();
	}

	@Test
	public void testAddOrder() throws Exception {
		when(orderService.addOrder(Mockito.isA(Order.class)))
				.thenReturn(new Order(221700, 12345, 12345, LocalDate.now()));
		String content = "{\"orderId\": 221700,\r\n" + "  \" customerId\": 12345,\r\n" + "  \"productId\": 12345,\r\n"
				+ "  \"date\": \"2015-05-03\"}";
		mockMvc.perform(post("/order").contentType(MediaType.APPLICATION_JSON).content(content)
				.accept(MediaType.APPLICATION_JSON)).andDo(print()).andExpect(status().isOk())
				.andExpect(jsonPath("$.orderId").exists()).andExpect(jsonPath("$.customerId").exists())
				.andExpect(jsonPath("$.productId").exists()).andExpect(jsonPath("$.date").exists())
				.andExpect(jsonPath("$.products").value("12345")).andExpect(jsonPath("$.customerId").value("12345"))
				.andDo(print());

	}

	@Test
	public void testgetOrderById() throws Exception {
		when(orderService.getOrderById(221700)).thenReturn(new Order(221700, 12345, 12345, LocalDate.now()));
		mockMvc.perform(MockMvcRequestBuilders.get("/order/221700").accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(jsonPath("$.orderId").exists())
				.andExpect(jsonPath("$.customerId").exists()).andExpect(jsonPath("$.productId").exists())
				.andExpect(jsonPath("$.date").exists()).andExpect(jsonPath("$.products").value("12345"))
				.andExpect(jsonPath("$.customerId").value("12345")).andDo(print());

	}
	/*
	 * public void testGetAllOrders() {
	 * when(orderService.getOrders()).thenReturn(value, values) } }
	 */

	@Test
	public void testDeleteOrder() throws Exception {
		Order order = new Order(221700, 12345, 12345, LocalDate.now());
		when(orderService.getOrderById(221700)).thenReturn(order);

		mockMvc.perform(delete("/order/221700").contentType(MediaType.APPLICATION_JSON_UTF8)
				.accept(MediaType.APPLICATION_JSON_UTF8)).andDo(print()).andExpect(status().isOk());

		verify(orderService, times(1)).deleteOrder(order);
	}
}

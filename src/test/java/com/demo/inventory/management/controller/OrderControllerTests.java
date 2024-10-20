package com.demo.inventory.management.controller;

import com.demo.inventory.management.entity.Order;
import com.demo.inventory.management.service.OrderServiceImpl;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebMvcTest(OrderController.class)
public class OrderControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private OrderServiceImpl orderService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void getOrdersShouldReturnAllOrders() throws Exception {
        Order order = Order.builder()
                .orderId("1")
                .created(new Date())
                .build();

        List<Order> orders = Arrays.asList(order);

        when(orderService.getAllOrders()).thenReturn(orders);

        mockMvc.perform(get("/api/orders").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").exists());
    }
}
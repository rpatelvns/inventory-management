package com.demo.inventory.management.service;

import com.demo.inventory.management.entity.Order;
import com.demo.inventory.management.entity.Stock;
import com.demo.inventory.management.repository.OrderRepository;
import com.demo.inventory.management.repository.StockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.mockito.BDDMockito.given;

import java.util.UUID;

@ExtendWith(MockitoExtension.class)
public class OrderServiceTests {
    @Mock
    private OrderRepository orderRepository;

    @InjectMocks
    private OrderServiceImpl orderService;

    private Order order;

    final String orderId = UUID.randomUUID().toString();

    @BeforeEach
    public void setup(){
        order = Order.builder()
                .orderId(orderId)
                .build();
    }

    @Test
    public void createOrderShouldCreateNewOrder(){
        given(orderRepository.save(order)).willReturn(order);

        Order newOrder = orderService.createOrder(order);

        Assertions.assertThat(newOrder).isNotNull();
    }
}

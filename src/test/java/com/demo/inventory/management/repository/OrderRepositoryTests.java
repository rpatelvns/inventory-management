package com.demo.inventory.management.repository;

import com.demo.inventory.management.entity.Order;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

@DataJpaTest
public class OrderRepositoryTests {
    @Autowired
    private OrderRepository orderRepository;

    private Order order;

    final String orderId = UUID.randomUUID().toString();

    @BeforeEach
    public void setup(){
        orderRepository.deleteAll();

        order = Order.builder()
                .orderId(orderId)
                .build();
    }

    @Test
    public void findAllShouldReturnAllOrders() {
        // given
        orderRepository.save(order);

        // when
        List<Order> orders = orderRepository.findAll();

        // then
        Assertions.assertThat(orders.size()).isEqualTo(1);
    }

    @Test
    public void getByReferenceShouldReturnRecord() {
        // given
        orderRepository.save(order);

        // when
        Order result = orderRepository.getReferenceById(orderId);

        // then
        Assertions.assertThat(result).isNotNull();
    }
}

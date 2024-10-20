package com.demo.inventory.management.repository;

import com.demo.inventory.management.entity.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, String> {
}

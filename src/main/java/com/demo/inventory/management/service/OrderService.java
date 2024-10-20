package com.demo.inventory.management.service;

import com.demo.inventory.management.entity.Order;
import com.demo.inventory.management.entity.OrderItem;
import com.demo.inventory.management.entity.Stock;
import com.demo.inventory.management.exception.OutOfStockException;
import com.demo.inventory.management.repository.OrderItemRepository;
import com.demo.inventory.management.repository.OrderRepository;
import com.demo.inventory.management.repository.StockRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

public interface OrderService{
    public OrderItem addOrderItem(String orderId, String skuId, Integer qty);
    public Order createOrder(Order newOrder);
    public List<Order> getAllOrders();
}

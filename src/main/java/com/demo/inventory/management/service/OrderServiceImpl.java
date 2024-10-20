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
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService{
    @Autowired
    OrderRepository orderRepository;

    @Autowired
    StockRepository stockRepository;

    @Autowired
    OrderItemRepository orderItemRepository;

    public OrderItem addOrderItem(String orderId, String skuId, Integer qty){
        //Check if stock is available to allocate
        //If request qty is higher than the available qty, throw out of stock exception
        Stock stock = stockRepository.getReferenceById(skuId);
        if(qty > stock.getQty()) {
            throw new OutOfStockException(String.format("SKU ID %s is out of stock", skuId));
        }

        OrderItem orderItem = OrderItem.builder()
                .skuId(skuId)
                .orderId(orderId)
                .build();

        return orderItemRepository.save(orderItem);
    }

    @Transactional
    public Order createOrder(Order newOrder){
        newOrder.setCreated(new Date());
        orderRepository.save(newOrder);

        //Add stock items to this order
        if (newOrder.getOrderItems() != null){
            for(OrderItem item: newOrder.getOrderItems()){
                addOrderItem(newOrder.getOrderId(), item.getSkuId(), item.getQty());
            }
        }

        return newOrder;
    }

    public List<Order> getAllOrders(){
        return orderRepository.findAll();
    }
}

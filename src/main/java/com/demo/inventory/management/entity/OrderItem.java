package com.demo.inventory.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "order_items")
public class OrderItem {
    @Id
    @GeneratedValue
    private Integer id;
    private String skuId;
    private Integer qty;
    private String orderId;

    @ManyToOne
    private Order order;
}
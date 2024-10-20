package com.demo.inventory.management.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class OrderDTO {
    private String orderId;
    private Date created;
    private Date updated;
    private List<OrderItemDTO> orderItems;
}

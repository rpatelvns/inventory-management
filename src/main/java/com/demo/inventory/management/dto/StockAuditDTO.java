package com.demo.inventory.management.dto;

import com.demo.inventory.management.entity.StockAuditStatus;
import com.demo.inventory.management.entity.StockUpdate;
import lombok.Data;

import java.util.Date;

@Data
public class StockAuditDTO {
    private Long id;
    private String skuId;
    private String orderId;
    private Integer qty;
    private Date created;
    private String message;
    private StockUpdate stockUpdate;
    private StockAuditStatus status;
}

package com.demo.inventory.management.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "stock_audits")
public class StockAudit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String skuId;
    private String orderId;
    private Integer qty;

    @Temporal(TemporalType.DATE)
    private Date created;
    private String message;

    @ManyToOne
    private Stock stock;

    @Enumerated(EnumType.ORDINAL)
    private StockUpdate stockUpdate;

    @Enumerated(EnumType.ORDINAL)
    private StockAuditStatus status;
}

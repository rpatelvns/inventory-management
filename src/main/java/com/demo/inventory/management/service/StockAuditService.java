package com.demo.inventory.management.service;


import com.demo.inventory.management.entity.Order;
import com.demo.inventory.management.entity.StockAudit;

import java.util.List;

public interface StockAuditService {
    public StockAudit logInwardSuccess(String skuId, Integer qty);

    public StockAudit logInwardFailure(String skuId, Integer qty, String errorMessage);

    public StockAudit logAllocationSuccess(String orderId, String skuId, Integer qty);

    public StockAudit logAllocationFailure(String orderId, String skuId, Integer qty, String errorMessage);

    public List<StockAudit> getStockAudits();
}

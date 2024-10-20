package com.demo.inventory.management.service;

import com.demo.inventory.management.entity.StockAudit;
import com.demo.inventory.management.entity.StockAuditStatus;
import com.demo.inventory.management.entity.StockUpdate;
import com.demo.inventory.management.repository.StockAuditRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class StockAuditServiceImpl implements StockAuditService{
    @Autowired
    StockAuditRepository stockAuditRepository;


    /*Record an inward stock movement, which like adding more qty to an existing SKU*/
    @Override
    public StockAudit logInwardSuccess(String skuId, Integer qty) {
        StockAudit stockAudit = StockAudit.builder()
                .stockUpdate(StockUpdate.INWARD)
                .created(new Date())
                .message("Updated stock successfully")
                .status(StockAuditStatus.COMPLETED)
                .skuId(skuId)
                .qty(qty)
                .build();

        return stockAuditRepository.save(stockAudit);
    }


    /*Record an inward stock movement failure, for exception scenarios*/
    @Override
    public StockAudit logInwardFailure(String skuId, Integer qty, String errorMessage) {
        StockAudit stockAudit = StockAudit.builder()
                .stockUpdate(StockUpdate.INWARD)
                .status(StockAuditStatus.FAILED)
                .created(new Date())
                .message(errorMessage)
                .skuId(skuId)
                .qty(qty)
                .build();

        return stockAuditRepository.save(stockAudit);
    }


    /*Record a successful an allocation request*/
    @Override
    public StockAudit logAllocationSuccess(String orderId, String skuId, Integer qty) {
        StockAudit stockAudit = StockAudit.builder()
                .stockUpdate(StockUpdate.ALLOCATION)
                .status(StockAuditStatus.COMPLETED)
                .orderId(orderId)
                .created(new Date())
                .message("Allocated stock successfully")
                .skuId(skuId)
                .qty(qty)
                .build();

        return stockAuditRepository.save(stockAudit);
    }


    /*Record a failed allocation request, for cases like out of stock*/
    @Override
    public StockAudit logAllocationFailure(String orderId, String skuId, Integer qty, String errorMessage) {
        StockAudit stockAudit = StockAudit.builder()
                .stockUpdate(StockUpdate.ALLOCATION)
                .status(StockAuditStatus.FAILED)
                .created(new Date())
                .orderId(orderId)
                .message(errorMessage)
                .skuId(skuId)
                .qty(qty)
                .build();

        return stockAuditRepository.save(stockAudit);
    }

    @Override
    public List<StockAudit> getStockAudits() {
        return stockAuditRepository.findAll();
    }
}

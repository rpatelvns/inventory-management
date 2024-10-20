package com.demo.inventory.management.audit;

import com.demo.inventory.management.entity.OrderItem;
import com.demo.inventory.management.entity.Stock;
import com.demo.inventory.management.service.StockAuditServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Aspect
@Component
@Slf4j
public class StockAuditAspect {
    @Autowired
    StockAuditServiceImpl stockAuditService;

    /*Pointcut to intercept add stock calls to audit stock inward movements*/
    @Pointcut("execution(* com.demo.inventory.management.service.StockService.addStock(..))")
    public void addStockService() {}

    /*Pointcut to intercept allocate stock calls to audit stock allocation movements*/
    @Pointcut("execution(* com.demo.inventory.management.service.OrderService.addOrderItem(..))")
    public void allocateStockService() {}


    /*If stock inward movement request was successful, log stock inward success.*/
    @AfterReturning(value = "addStockService() && args(skuId, qty)", returning = "stock")
    public void afterStockUpdate(JoinPoint jp, Object stock, String skuId, Integer qty) {
        try {
            Stock updateStock = (Stock) stock;
            stockAuditService.logInwardSuccess(skuId, qty);

            log.info(String.format("Added stock for %s", updateStock.getSkuName()));

        } catch (Exception exception) {
            log.warn("Error occurred while auditing stock update! {}", exception.getMessage());
        }
    }


    /*If there is any exception like out of stock for allocation request, log allocation failures.*/
    @AfterThrowing(pointcut = "allocateStockService() && args(orderId, skuId, qty)", throwing = "e")
    public void afterThrowingException(JoinPoint joinPoint, Exception e, String orderId, String skuId, Integer qty) {
        log.error(String.format("Allocation failed for SKU %s for %d", skuId, qty));
        stockAuditService.logAllocationFailure(orderId, skuId, qty, e.getMessage());
    }


    /*If stock allocation request was successful, log allocation success.*/
    @AfterReturning(value = "allocateStockService() && args(orderId, skuId, qty)", returning = "orderItem")
    public void afterStockAllocation(JoinPoint jp, Object orderItem, String orderId, String skuId, Integer qty) {
        try {
            OrderItem updateOrderItem = (OrderItem) orderItem;
            stockAuditService.logAllocationSuccess(orderId, skuId, qty);

            log.info(String.format("Allocated stock %s:%d for Order %s", updateOrderItem.getSkuId(), updateOrderItem.getQty(), updateOrderItem.getOrderId()));

        } catch (Exception exception) {
            log.warn("Error occurred while auditing stock update! {}", exception.getMessage());
        }
    }
}
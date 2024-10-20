package com.demo.inventory.management.repository;

import com.demo.inventory.management.entity.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface StockRepository extends JpaRepository<Stock, String> {
    public List<Stock> findBySkuNameContainsIgnoreCase(String skuName);
}

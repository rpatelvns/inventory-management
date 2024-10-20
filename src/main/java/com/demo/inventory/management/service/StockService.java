package com.demo.inventory.management.service;

import com.demo.inventory.management.entity.Stock;

import java.util.List;

public interface StockService {
    public List<Stock> getAllStocks();
    public Stock getById(String skuId);

    public Stock createStock(Stock stock);

    public Stock addStock(String skuId, Integer qty);

    public List<Stock> findStockByName(String skuName);
}

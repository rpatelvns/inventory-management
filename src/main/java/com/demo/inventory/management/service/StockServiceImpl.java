package com.demo.inventory.management.service;

import com.demo.inventory.management.entity.Stock;
import com.demo.inventory.management.exception.IllegalStockException;
import com.demo.inventory.management.exception.ResourceNotFoundException;
import com.demo.inventory.management.repository.StockRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StockServiceImpl implements StockService{
    @Autowired
    StockRepository stockRepository;

    public List<Stock> getAllStocks() {
        return stockRepository.findAll();
    }

    public Stock getById(String skuId) {
        return stockRepository.getReferenceById(skuId);
    }

    public Stock createStock(Stock stock) {
        return stockRepository.save(stock);
    }

    public Stock addStock(String skuId, Integer qty) {
        try{
            Stock  stock = stockRepository.getReferenceById(skuId);
            stock.setQty(stock.getQty() + qty);

            return stockRepository.save(stock);
        } catch (EntityNotFoundException e){
          throw new ResourceNotFoundException(String.format("Stock with id %s was not found!", skuId));
        }
    }

    public List<Stock> findStockByName(String skuName) {
        return stockRepository.findBySkuNameContainsIgnoreCase(skuName);
    }
}

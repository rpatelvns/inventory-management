package com.demo.inventory.management.service;

import com.demo.inventory.management.entity.Order;
import com.demo.inventory.management.entity.Stock;
import com.demo.inventory.management.repository.StockRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

import static org.mockito.BDDMockito.given;

@ExtendWith(MockitoExtension.class)
public class StockServiceTests {
    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    private Stock stock;

    final String skuId = UUID.randomUUID().toString();

    @BeforeEach
    public void setup(){
        stockRepository.deleteAll();

        stock = Stock.builder()
                .skuId(skuId)
                .skuName("Product 1")
                .qty(10)
                .build();
    }

    @Test
    public void getByIdShouldReturnStockBySkuId(){
        given(stockRepository.getReferenceById(skuId)).willReturn(stock);

        Stock stock = stockService.getById(skuId);

        Assertions.assertThat(stock).isNotNull();
    }

    @Test
    public void findStockByNameShouldReturnMatchingRecords(){
        given(stockRepository.findBySkuNameContainsIgnoreCase("Product 1")).willReturn(Arrays.asList(stock));

        List<Stock> stocks = stockService.findStockByName("Product 1");

        Assertions.assertThat(stocks).isNotNull();
    }
}

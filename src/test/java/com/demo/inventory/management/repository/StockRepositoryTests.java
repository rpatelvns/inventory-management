package com.demo.inventory.management.repository;

import com.demo.inventory.management.entity.Stock;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.UUID;

@DataJpaTest
public class StockRepositoryTests {
    @Autowired
    private StockRepository stockRepository;

    private Stock stock;

    final String skuId = UUID.randomUUID().toString();

    @BeforeEach
    public void setup(){
        stockRepository.deleteAll();

        stock = Stock.builder()
                .skuId(skuId)
                .skuName("TEST SKU")
                .qty(10)
                .build();
    }

    @Test
    public void findBySkuNameShouldReturnMatchingRecords() {
        // given
        stockRepository.save(stock);

        // when
        List<Stock> stocks = stockRepository.findBySkuNameContainsIgnoreCase("TEST");

        // then
        Assertions.assertThat(stocks.size()).isEqualTo(1);
    }

    @Test
    public void findAllShouldReturnAllRecords() {
        // given
        stockRepository.save(stock);

        // when
        List<Stock> stocks = stockRepository.findAll();

        // then
        Assertions.assertThat(stocks.size()).isEqualTo(1);
    }


    @Test
    public void getByReferenceShouldReturnRecord() {
        // given
        stockRepository.save(stock);

        // when
        Stock stock = stockRepository.getReferenceById(skuId);

        // then
        Assertions.assertThat(stock).isNotNull();
    }
}

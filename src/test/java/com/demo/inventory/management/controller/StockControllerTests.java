package com.demo.inventory.management.controller;

import com.demo.inventory.management.entity.Order;
import com.demo.inventory.management.entity.Stock;
import com.demo.inventory.management.service.OrderServiceImpl;
import com.demo.inventory.management.service.StockServiceImpl;
import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(StockController.class)
public class StockControllerTests {
    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private StockServiceImpl stockService;

    @MockBean
    private ModelMapper modelMapper;

    @Test
    public void getOrdersShouldReturnAllOrders() throws Exception {
        Stock stock = Stock.builder()
                .skuId("1")
                .skuName("TEST")
                .build();

        List<Stock> stocks = Arrays.asList(stock);

        when(stockService.getAllStocks()).thenReturn(stocks);

        mockMvc.perform(get("/api/stocks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.success").exists());
    }
}
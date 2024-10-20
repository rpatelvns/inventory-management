package com.demo.inventory.management.controller;

import com.demo.inventory.management.dto.StockDTO;
import com.demo.inventory.management.entity.Stock;
import com.demo.inventory.management.response.ApiResponse;

import com.demo.inventory.management.service.StockServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stocks")
public class StockController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StockServiceImpl stockService;

    @Operation(summary = "Get all stocks")
    @GetMapping
    List<StockDTO> getAllStocks(){
        return stockService.getAllStocks()
                .stream()
                .map(stock -> modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());
    }


    @Operation(summary = "Search SKUs by name")
    @GetMapping("/search")
    List<StockDTO> searchStock(@Parameter(description = "SKU Name") @RequestParam String skuName){
        return stockService.findStockByName(skuName)
                .stream()
                .map(stock -> modelMapper.map(stock, StockDTO.class))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Create new stock record")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Stock created successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = StockDTO.class))
            }),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PostMapping
    ApiResponse<?> createStock(@Valid @RequestBody StockDTO stockDTO){
        Stock stock = stockService.createStock(modelMapper.map(stockDTO, Stock.class));
        if (stock != null){
            return ApiResponse.success(stock, "Created stock successfully!!");
        }

        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Error creating new stock!!");
    }


    @Operation(summary = "Update an existing stock. e.g. add stock qty")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Stock updated successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = StockDTO.class))
                    }),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PutMapping
    ApiResponse<?> updateStock(@Valid @RequestBody StockDTO stockDTO){
        Stock stock = stockService.addStock(stockDTO.getSkuId(), stockDTO.getQty());
        if (stock != null){
            return ApiResponse.success(stock, "Updated stock successfully!!");
        }

        return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, "Error updating stock!!");
    }
}

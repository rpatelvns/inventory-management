package com.demo.inventory.management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class StockDTO {
    private String skuId;

    @Size(min = 2, message = "SKU Name should have at least 2 characters")
    private String skuName;

    @Min(value = 0L, message = "The quantity should be a positive number")
    private Integer qty;
}

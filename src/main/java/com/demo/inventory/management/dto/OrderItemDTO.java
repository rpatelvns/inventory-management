package com.demo.inventory.management.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class OrderItemDTO {
    private Integer id;

    @NotEmpty
    @Size(min = 5, message = "SKU ID should have at least 5 characters")
    private String skuId;

    @NotEmpty
    @Min(value = 0L, message = "The quantity should be a positive number")
    private Integer qty;
}

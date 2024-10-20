package com.demo.inventory.management.controller;

import com.demo.inventory.management.dto.OrderDTO;
import com.demo.inventory.management.entity.Order;
import com.demo.inventory.management.entity.OrderItem;
import com.demo.inventory.management.response.ApiResponse;
import com.demo.inventory.management.service.OrderServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
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
@RequestMapping("/api/orders")
public class OrderController {

    @Autowired
    ModelMapper modelMapper;

    @Autowired
    OrderServiceImpl orderService;

    @Operation(summary = "Get all orders")
    @GetMapping
    List<OrderDTO> getOrders(){
        return orderService.getAllOrders()
                .stream()
                .map(order -> modelMapper.map(order, OrderDTO.class))
                .collect(Collectors.toList());
    }

    @Operation(summary = "Update an existing order. e.g. allocate stock to an order")
    @PutMapping
    ApiResponse<?> allocateStock(@Parameter(description = "SKU ID")
                                 @RequestParam String skuId,
                                 @Parameter(description = "Order Id")
                                 @RequestParam String orderId,
                                 @Parameter(description = "SKU Quantities")
                                 @RequestParam Integer qty){

        OrderItem orderItem = orderService.addOrderItem(orderId, skuId, qty);
        if (orderItem == null){
            return ApiResponse.error(HttpStatus.INTERNAL_SERVER_ERROR, String.format("Failed to allocate SKU %s to order %s", skuId, orderId));
        }

        return ApiResponse.success(HttpStatus.OK, String.format("Allocated SKU to order %s", orderId));
    }

    @Operation(summary = "Create an new order")
    @ApiResponses(value = {
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "200", description = "Order created successfully",
                    content = { @Content(mediaType = "application/json", schema = @Schema(implementation = OrderDTO.class))
            }),
            @io.swagger.v3.oas.annotations.responses.ApiResponse(responseCode = "400", description = "Invalid input provided")
    })
    @PostMapping
    ApiResponse<?> createOrder(
            @io.swagger.v3.oas.annotations.parameters.RequestBody(
                    description = "Create an order", required = true,
                    content = @Content(mediaType = "application/json",
                            schema = @Schema(implementation = OrderDTO.class),
                            examples = @ExampleObject(value = "{}")
                    )
            ) @Valid @RequestBody OrderDTO orderDTO){

        Order newOrder = orderService.createOrder(modelMapper.map(orderDTO, Order.class));
        return ApiResponse.builder()
                .data(newOrder)
                .status(HttpStatus.CREATED)
                .build();
    }
}

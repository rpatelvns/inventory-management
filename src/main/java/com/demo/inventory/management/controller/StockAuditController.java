package com.demo.inventory.management.controller;

import com.demo.inventory.management.dto.StockAuditDTO;
import com.demo.inventory.management.service.StockAuditServiceImpl;
import io.swagger.v3.oas.annotations.Operation;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/stock-audits")
public class StockAuditController {

    @Autowired
    private ModelMapper modelMapper;

    @Autowired
    private StockAuditServiceImpl stockAuditService;

    @Operation(summary = "Get all stock audit records")
    @GetMapping
    List<StockAuditDTO> getStockAudits(){
        return stockAuditService.getStockAudits()
                .stream()
                .map(stock -> modelMapper.map(stock, StockAuditDTO.class))
                .collect(Collectors.toList());
    }
}

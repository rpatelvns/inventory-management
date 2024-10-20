package com.demo.inventory.management.repository;

import com.demo.inventory.management.entity.StockAudit;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StockAuditRepository extends JpaRepository<StockAudit, Long> {

}

package com.demo.inventory.management;

import com.demo.inventory.management.controller.OrderController;
import com.demo.inventory.management.controller.StockAuditController;
import com.demo.inventory.management.controller.StockController;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class InventoryManagementApplicationTests {

	@Autowired
	OrderController orderController;

	@Autowired
	StockController stockController;

	@Autowired
	StockAuditController stockAuditController;

	@Test
	public void contextLoads() {
		Assertions.assertThat(orderController).isNotNull();
		Assertions.assertThat(stockController).isNotNull();
		Assertions.assertThat(stockAuditController).isNotNull();
	}
}

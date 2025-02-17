package com.inventry_service;

import com.inventry_service.model.Inventory;
import com.inventry_service.repository.InventoryRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableDiscoveryClient
public class InventoryApplication {

	public static void main(String[] args) {
		SpringApplication.run(InventoryApplication.class, args);
	}

	@Bean
	public CommandLineRunner loadData(InventoryRepository inventoryRepository ){
		return args -> {
			Inventory inventory = new Inventory();
			inventory.setInventorySkuCode("IPHONE_13");
			inventory.setInventoryQuantity(100);

			Inventory inventory1 = new Inventory();
			inventory1.setInventorySkuCode("IPHONE_13_RED");
			inventory1.setInventoryQuantity(0);

			inventoryRepository.save(inventory);
			inventoryRepository.save(inventory1);
		};
	}
}

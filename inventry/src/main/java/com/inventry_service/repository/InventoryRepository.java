package com.inventry_service.repository;

import com.inventry_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    Optional<Inventory> findByInventorySkuCode(String skuCode);
}

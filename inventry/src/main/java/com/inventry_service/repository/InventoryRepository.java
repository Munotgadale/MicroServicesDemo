package com.inventry_service.repository;

import com.inventry_service.model.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface InventoryRepository extends JpaRepository<Inventory,Long> {

    List<Inventory> findByInventorySkuCodeIn(List<String> skuCode);
}

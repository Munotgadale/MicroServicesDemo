package com.inventry_service.service;

import com.inventry_service.dto.InventoryResponse;
import com.inventry_service.repository.InventoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class InventoryServiceImpl implements InventoryService{

    public final InventoryRepository inventoryRepository;

    @Override
    @Transactional(readOnly = true)
    public List<InventoryResponse> isInStock(List<String> skuCode) {
        return inventoryRepository.findByInventorySkuCodeIn(skuCode).stream()
                .map(Inventory ->
                    InventoryResponse.builder()
                            .inventorySkuCode(Inventory.getInventorySkuCode())
                            .isInStock((Inventory.getInventoryQuantity() > 0))
                            .build()
                ).toList();
    }
}

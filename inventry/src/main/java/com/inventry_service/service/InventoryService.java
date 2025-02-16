package com.inventry_service.service;

import com.inventry_service.dto.InventoryResponse;

import java.util.List;

public interface InventoryService {

    public List<InventoryResponse> isInStock(List<String> skuCode);
}

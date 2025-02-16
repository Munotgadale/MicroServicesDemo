package com.order.service;

import com.order.dto.OrderRequest;

public interface OrderService {

    public void placeOrder(OrderRequest orderRequest);
}

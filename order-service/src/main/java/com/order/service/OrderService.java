package com.order.service;

import com.order.dto.OrderRequest;

public interface OrderService {

    public String placeOrder(OrderRequest orderRequest);
}

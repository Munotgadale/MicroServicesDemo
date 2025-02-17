package com.order.service;

import com.order.dto.InventoryResponse;
import com.order.dto.OrderLineItemsDto;
import com.order.dto.OrderRequest;
import com.order.model.Order;
import com.order.model.OrderLineItems;
import com.order.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class OrderServiceImpl implements OrderService{

    public final OrderRepository orderRepository;

    public final WebClient.Builder webClientBuilder;

    public String placeOrder(OrderRequest orderRequest){
        Order order = new Order();
        order.setOrderNumber(UUID.randomUUID().toString());

       List<OrderLineItems> orderLineItemsList = orderRequest.getOrderLineItemsDtoList()
                .stream()
                .map(this::mapToDto)
                .toList();

       order.setOrderLineItemsList(orderLineItemsList);

       List<String> skuCodesList = order.getOrderLineItemsList().stream()
               .map(OrderLineItems::getSkuCode)
               .toList();

       //Call Inventory Service
        InventoryResponse[] inventoryResponseArray = webClientBuilder.build().get()
                .uri("http://inventry/api/inventory/get-inventory",
                        uriBuilder -> uriBuilder.queryParam("skuCode",skuCodesList).build())
                        .retrieve()
                                .bodyToMono(InventoryResponse[].class)
                                        .block();


        boolean allProductsInStock = Arrays.stream(inventoryResponseArray).allMatch(InventoryResponse::isInStock);
        if(allProductsInStock) {
            orderRepository.save(order);
            log.info("Order Placed Successfully with Order Id: {}", order.getOrderId());
            return "Order Placed Successfully with Order Id "+order.getOrderId();
        }else{
            log.info("Order Not Placed, Product Is Not in Stock Result: {}", false);
            return "Order Not Placed, Product Is Not in Stock";
        }
    }

    private OrderLineItems mapToDto(OrderLineItemsDto orderLineItemsDto) {
        OrderLineItems orderLineItems = new OrderLineItems();
        orderLineItems.setPrice(orderLineItemsDto.getPrice());
        orderLineItems.setQuantity(orderLineItemsDto.getQuantity());
        orderLineItems.setSkuCode(orderLineItemsDto.getSkuCode());
        return  orderLineItems;
    }

}

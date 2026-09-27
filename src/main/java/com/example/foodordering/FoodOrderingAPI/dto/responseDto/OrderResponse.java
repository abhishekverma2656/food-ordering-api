package com.example.foodordering.FoodOrderingAPI.dto.responseDto;

import lombok.Data;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@Data
public class OrderResponse {

    private Long orderId;
    private LocalDateTime dateTime;
    private String orderStatus;
    private BigDecimal totalPrice;

    private RestaurantSummaryResponse restaurant;
    private String paymentStatus;
    private AddressResponse address;
    private List<OrderItemResponse> items;
}

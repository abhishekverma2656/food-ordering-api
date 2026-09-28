package com.example.foodordering.FoodOrderingAPI.controller;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreateOrderRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.OrderResponse;
import com.example.foodordering.FoodOrderingAPI.services.OrderService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping
    public OrderResponse createOrder(
            @Valid @RequestBody CreateOrderRequest request) {

        return orderService.createOrder(request);
    }

    @GetMapping("/{id}")
    public OrderResponse getOrderById(
            @PathVariable Long id) {

        return orderService.getOrderById(id);
    }

    @GetMapping("/user/{userId}")
    public List<OrderResponse> getOrdersByUser(
            @PathVariable Long userId) {

        return orderService.getOrdersByUser(userId);
    }

    @PatchMapping("/{orderId}/status")
    public OrderResponse updateOrderStatus(
            @PathVariable Long orderId,
            @RequestParam String status) {

        return orderService.updateOrderStatus(orderId, status);
    }

    @PatchMapping("/{orderId}/cancel")
    public OrderResponse cancelOrder(
            @PathVariable Long orderId) {

        return orderService.cancelOrder(orderId);
    }
}
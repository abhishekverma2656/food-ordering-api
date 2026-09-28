package com.example.foodordering.FoodOrderingAPI.controller;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreatePaymentRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.PaymentResponse;
import com.example.foodordering.FoodOrderingAPI.services.PaymentService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    public final PaymentService paymentService;

    @PostMapping("/orders/{orderId}/payment")
    public PaymentResponse createPayment(
            @PathVariable Long orderId,
            @Valid @RequestBody CreatePaymentRequest request) {

        return paymentService.createPayment(orderId, request);
    }

    @GetMapping("/{id}")
    public PaymentResponse getPaymentById(@PathVariable Long id){
        return paymentService.getPaymentById(id);
    }
    @GetMapping("/orders/{orderId}/payment")
    public PaymentResponse getPaymentByOrderId(
            @PathVariable Long orderId) {

        return paymentService.getPaymentByOrderId(orderId);
    }

    @PatchMapping("/{paymentId}/status")
    public PaymentResponse updatePaymentStatus(
            @PathVariable Long paymentId,
            @RequestParam String status) {

        return paymentService.updatePaymentStatus(paymentId, status);
    }





}

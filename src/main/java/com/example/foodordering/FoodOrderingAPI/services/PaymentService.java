package com.example.foodordering.FoodOrderingAPI.services;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreatePaymentRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.PaymentResponse;
import com.example.foodordering.FoodOrderingAPI.exception.ResourceNotFoundException;
import com.example.foodordering.FoodOrderingAPI.mapper.PaymentMapper;
import com.example.foodordering.FoodOrderingAPI.models.Order;
import com.example.foodordering.FoodOrderingAPI.models.Payment;
import com.example.foodordering.FoodOrderingAPI.repository.OrderRepository;
import com.example.foodordering.FoodOrderingAPI.repository.PaymentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final OrderRepository orderRepository;

    public PaymentResponse createPayment(
            Long orderId,
            CreatePaymentRequest paymentRequest) {

        Order order = orderRepository.findById(orderId)
                .orElseThrow(() ->
                        new ResourceNotFoundException(
                                "Order not found with id: " + orderId
                        ));

        Payment payment = paymentMapper.toEntity(paymentRequest);

        payment.setOrder(order);
        payment.setAmount(order.getTotalPrice());
        payment.setTransactionId(UUID.randomUUID().toString());
        payment.setPaymentDateTime(LocalDateTime.now());
        payment.setStatus("PENDING");

        Payment savedPayment = paymentRepository.save(payment);

        return paymentMapper.toResponse(savedPayment);
    }

    public PaymentResponse getPaymentById(Long id){
        return paymentMapper.toResponse(paymentRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Payment not found with id: " + id)));

    }

    public PaymentResponse getPaymentByOrderId(Long orderId){
        return paymentMapper.toResponse(paymentRepository.findByOrderId(orderId)
                .orElseThrow(()-> new ResourceNotFoundException("Payment not found with id: " + orderId)));

    }

     public PaymentResponse updatePaymentStatus(Long paymentId, String status){
         Payment payment = paymentRepository.findById(paymentId)
                 .orElseThrow(() ->
                         new ResourceNotFoundException(
                                 "Payment not found with id: " + paymentId
                         ));

         payment.setStatus(status);

         Payment updatedPayment = paymentRepository.save(payment);

         return paymentMapper.toResponse(updatedPayment);

     }


}



package com.example.foodordering.FoodOrderingAPI.mapper;


import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreatePaymentRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.PaymentResponse;
import com.example.foodordering.FoodOrderingAPI.models.Payment;
import org.mapstruct.Mapper;

@Mapper (componentModel = "spring")
public interface PaymentMapper {

    Payment toEntity(CreatePaymentRequest paymentRequest);

    PaymentResponse toResponse(Payment payment);
}

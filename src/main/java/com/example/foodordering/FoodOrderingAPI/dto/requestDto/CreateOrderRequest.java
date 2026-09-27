package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;

import java.util.List;
@Data
public class CreateOrderRequest {
    @NotBlank
    private Long restaurantId;
    @NotBlank @Valid
    private Long addressId;
    @NotEmpty
    @Valid
    private List<OrderItemRequest> items;
}

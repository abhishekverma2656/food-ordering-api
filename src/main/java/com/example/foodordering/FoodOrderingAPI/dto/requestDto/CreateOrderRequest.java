package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import jakarta.validation.Valid;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

import java.util.List;
@Data
public class CreateOrderRequest {
    @NotNull
    private Long restaurantId;

    @NotNull
    private Long userId;

    @NotNull
    @Valid
    private Long addressId;
    @NotEmpty
    @Valid
    private List<OrderItemRequest> items;
}

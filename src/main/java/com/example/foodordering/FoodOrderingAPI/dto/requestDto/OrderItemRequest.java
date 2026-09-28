package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class OrderItemRequest {
    @NotNull
    private Long itemId;
    @Positive
    private Integer quantity;
}

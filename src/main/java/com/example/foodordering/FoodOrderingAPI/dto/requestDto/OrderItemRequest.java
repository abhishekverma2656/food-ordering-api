package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class OrderItemRequest {
    @NotBlank
    private Long itemId;
    @NotBlank
    private Integer quantity;
}

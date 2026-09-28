package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class CreateItemRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @Positive
    private BigDecimal price;

    private String description;
}

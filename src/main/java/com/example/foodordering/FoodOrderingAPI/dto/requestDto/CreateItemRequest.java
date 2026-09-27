package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Positive;
import lombok.Data;

@Data
public class CreateItemRequest {
    @NotBlank
    private String name;
    @NotBlank
    private String type;
    @NotBlank @Positive
    private Double price;

    private String description;
}

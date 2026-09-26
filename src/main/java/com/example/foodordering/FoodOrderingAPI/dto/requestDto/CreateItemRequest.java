package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import lombok.Data;

@Data
public class CreateItemRequest {
    private String name;
    private String type;
    private Double price;
    private String description;
}

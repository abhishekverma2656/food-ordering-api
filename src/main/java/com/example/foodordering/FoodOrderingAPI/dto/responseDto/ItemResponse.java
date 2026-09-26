package com.example.foodordering.FoodOrderingAPI.dto.responseDto;

import lombok.Data;

@Data
public class ItemResponse {
    private String name;
    private String type;
    private Double price;
    private String description;
}

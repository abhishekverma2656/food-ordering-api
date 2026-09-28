package com.example.foodordering.FoodOrderingAPI.dto.responseDto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class ItemResponse {
    private String name;
    private String type;
    private BigDecimal price;
    private String description;
}

package com.example.foodordering.FoodOrderingAPI.dto.responseDto;

import lombok.Data;

@Data
public class RestaurantResponse {
    private Long id;
    private String name;
    private String contactNo;
    private String email;
}


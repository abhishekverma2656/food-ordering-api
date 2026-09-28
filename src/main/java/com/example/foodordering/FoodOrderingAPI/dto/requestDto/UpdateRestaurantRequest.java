package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class UpdateRestaurantRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String contactNo;

    @NotBlank @Email
    private String email;
}

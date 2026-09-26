package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import lombok.Data;

@Data
public class RegisterUserRequest {

    private String name;
    private String contactNo;
    private String email;
    private String password;

}

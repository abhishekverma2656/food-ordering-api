package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserRequest {
    @NotBlank
    private String name;

    @NotBlank
    private String contactNo;

    @NotBlank @Email
    private String email;

    @NotBlank
    @Size(min = 8, max = 100)
    private String password;

}

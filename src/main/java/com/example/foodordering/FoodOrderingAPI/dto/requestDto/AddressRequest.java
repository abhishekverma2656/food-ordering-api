package com.example.foodordering.FoodOrderingAPI.dto.requestDto;

import lombok.Data;

@Data
public class AddressRequest {

    private String pinCode;
    private String city;
    private String area;
    private String landmark;
}

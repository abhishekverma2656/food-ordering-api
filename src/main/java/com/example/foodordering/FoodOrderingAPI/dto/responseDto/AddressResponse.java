package com.example.foodordering.FoodOrderingAPI.dto.responseDto;

import lombok.Data;

@Data
public class AddressResponse {
    private String pinCode;
    private String city;
    private String area;
    private String landmark;
}

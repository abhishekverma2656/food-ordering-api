package com.example.foodordering.FoodOrderingAPI.mapper;


import com.example.foodordering.FoodOrderingAPI.dto.requestDto.AddressRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.AddressResponse;
import com.example.foodordering.FoodOrderingAPI.models.Address;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface AddressMapper {

    Address toEntity(AddressRequest addressRequest);

    AddressResponse toResponse(Address address);


}

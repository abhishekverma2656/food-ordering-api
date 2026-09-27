package com.example.foodordering.FoodOrderingAPI.mapper;


import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreateRestaurantRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.RestaurantResponse;
import com.example.foodordering.FoodOrderingAPI.models.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface RestaurantMapper {


    Restaurant toEntity(CreateRestaurantRequest restaurantRequest);

    RestaurantResponse toResponse(Restaurant restaurant);
}


package com.example.foodordering.FoodOrderingAPI.mapper;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreateOrderRequest;
import com.example.foodordering.FoodOrderingAPI.dto.requestDto.OrderItemRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.AddressResponse;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.OrderItemResponse;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.OrderResponse;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.RestaurantSummaryResponse;
import com.example.foodordering.FoodOrderingAPI.models.Address;
import com.example.foodordering.FoodOrderingAPI.models.Order;
import com.example.foodordering.FoodOrderingAPI.models.OrderItem;
import com.example.foodordering.FoodOrderingAPI.models.Restaurant;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface OrderMapper {

    Order toEntity(CreateOrderRequest createOrderRequest);
    OrderItem toEntity(OrderItemRequest orderItemRequest);


    OrderResponse toResponse(Order order);

    RestaurantSummaryResponse toRestaurantSummary(Restaurant restaurant);

    AddressResponse toAddressResponse(Address address);

    OrderItemResponse toOrderItemResponse(OrderItem orderItem);
}

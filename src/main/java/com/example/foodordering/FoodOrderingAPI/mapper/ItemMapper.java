package com.example.foodordering.FoodOrderingAPI.mapper;


import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreateItemRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.ItemResponse;
import com.example.foodordering.FoodOrderingAPI.models.Item;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ItemMapper {

    Item toEntity(CreateItemRequest itemRequest);

    ItemResponse toResponse(Item item);
}

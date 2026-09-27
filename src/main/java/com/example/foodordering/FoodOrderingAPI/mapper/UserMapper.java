package com.example.foodordering.FoodOrderingAPI.mapper;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.RegisterUserRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.UserResponse;
import com.example.foodordering.FoodOrderingAPI.models.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    User toEntity(RegisterUserRequest userRequest);

    UserResponse toResponse(User user);
}

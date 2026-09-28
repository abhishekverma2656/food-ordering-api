package com.example.foodordering.FoodOrderingAPI.controller;

import com.example.foodordering.FoodOrderingAPI.dto.requestDto.CreateRestaurantRequest;
import com.example.foodordering.FoodOrderingAPI.dto.requestDto.UpdateRestaurantRequest;
import com.example.foodordering.FoodOrderingAPI.dto.responseDto.RestaurantResponse;
import com.example.foodordering.FoodOrderingAPI.services.RestaurantService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/restaurants")
@RequiredArgsConstructor
public class RestaurantController {

    private final RestaurantService restaurantService;

    @PostMapping
    public RestaurantResponse createRestaurant(@Valid @RequestBody CreateRestaurantRequest restaurantRequest){
       return restaurantService.addRestaurant(restaurantRequest);
    }


    @GetMapping("/{id}")
    public RestaurantResponse getRestaurantById(@PathVariable Long id){
        return restaurantService.getRestaurantById(id);
    }

    @PutMapping("/{id}")
    public RestaurantResponse updateRestaurant(@PathVariable Long id, @Valid @RequestBody UpdateRestaurantRequest request){
        return restaurantService.updateRestaurantById(id, request);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteRestaurant(@PathVariable Long id){
        restaurantService.deleteRestaurantById(id);
        return ResponseEntity.noContent().build();
    }
}

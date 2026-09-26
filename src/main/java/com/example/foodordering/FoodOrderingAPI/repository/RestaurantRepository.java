package com.example.foodordering.FoodOrderingAPI.repository;

import com.example.foodordering.FoodOrderingAPI.models.Restaurant;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RestaurantRepository extends JpaRepository<Restaurant,Long> {
}

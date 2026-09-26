package com.example.foodordering.FoodOrderingAPI.repository;

import com.example.foodordering.FoodOrderingAPI.models.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemRepository extends JpaRepository<Item,Long> {
}

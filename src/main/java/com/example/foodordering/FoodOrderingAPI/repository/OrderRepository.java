package com.example.foodordering.FoodOrderingAPI.repository;

import com.example.foodordering.FoodOrderingAPI.models.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<OrderItem,Long> {

}

package com.example.foodordering.FoodOrderingAPI.repository;

import com.example.foodordering.FoodOrderingAPI.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}

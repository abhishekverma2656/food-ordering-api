package com.example.foodordering.FoodOrderingAPI.repository;

import com.example.foodordering.FoodOrderingAPI.models.Payment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PaymentRepository extends JpaRepository<Payment,Long> {
}

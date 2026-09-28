package com.example.foodordering.FoodOrderingAPI.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name="payments")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false,unique = true)
    private String transactionId;

    @Column(nullable = false)
    private BigDecimal amount;

    @Column(nullable = false)
    private LocalDateTime paymentDateTime;

    @Column(nullable = false)
    private String paymentMethod;

    @Column(nullable = false)
    private String status; //SUCCESS,FAILED,PENDING

    @OneToOne
    @JoinColumn(name = "order_id")
    private Order order;










}

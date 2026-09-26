package com.example.foodordering.FoodOrderingAPI.models;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private BigDecimal totalPrice;

    @Column(nullable = false)
    private LocalDateTime dateTime;

    @Column(nullable = false)
    private String orderStatus;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @OneToOne(mappedBy = "order")
    private Payment payment;

    @ManyToOne
    @JoinColumn(name="address_id")
    private Address address;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;


}

package com.example.foodordering.FoodOrderingAPI.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Address {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column (nullable = false)
    private String pinCode;

    @Column (nullable = false)
    private String city;

    @Column (nullable = false)
    private String area;

    @Column (nullable = false)
    private String landmark;

    @ManyToOne
    @JoinColumn(name="user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name="restaurant_id")
    private Restaurant restaurant;

}

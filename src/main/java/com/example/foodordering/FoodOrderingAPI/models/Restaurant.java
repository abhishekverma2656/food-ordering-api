package com.example.foodordering.FoodOrderingAPI.models;


import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Entity
@Table(name="restaurants")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Restaurant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false ,unique = true)
    private String contactNo;

    @Column(nullable = false ,unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @OneToOne(mappedBy = "restaurant")
    private Address addresses ;

    @OneToMany(mappedBy = "restaurant")
    private List <Order> orders;

    @OneToMany(mappedBy = "restaurant")
    private List<Item> items;

}

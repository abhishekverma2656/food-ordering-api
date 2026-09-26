package com.example.foodordering.FoodOrderingAPI.models;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name="orderItem")
@Data
@NoArgsConstructor
@AllArgsConstructor

public class OrderItem {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Integer quantity;

    @Column(nullable = false)
    private Double price;

    @ManyToOne
    @JoinColumn(name="item_id")
    private  Item item;

    @ManyToOne
    @JoinColumn(name="order_id")
    private Order order;

}

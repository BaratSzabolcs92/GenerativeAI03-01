package com.example.GenerativeAI0301.model;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
@Setter
@Getter
@Entity
public class Order {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long orderId;

    @ManyToOne
    @JoinColumn(name="userId", nullable=false)
    private User user;

    @ManyToMany
    @JoinTable(name = "order_product", joinColumns = @JoinColumn(name = "orderId"), inverseJoinColumns = @JoinColumn(name = "productId"))
    private List<Product> products;

    private double totalAmount;
    private Date orderDate;
    private String orderStatus;

    // Getters and Setters
}

package com.example.corporatesolution.model;

import jakarta.persistence.*;
import lombok.Data;

@Data
@Table(name = "product")
@Entity
public final class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String quantity;
    private double price;
    @Transient
    private  double totalPrice;
}

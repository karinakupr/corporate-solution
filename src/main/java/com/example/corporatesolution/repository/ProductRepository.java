package com.example.corporatesolution.repository;

import com.example.corporatesolution.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;


public interface ProductRepository extends JpaRepository<Product,Long> { }
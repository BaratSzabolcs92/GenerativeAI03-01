package com.example.GenerativeAI0301.dao;

import com.example.GenerativeAI0301.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProductDao extends JpaRepository<Product, Long> { }

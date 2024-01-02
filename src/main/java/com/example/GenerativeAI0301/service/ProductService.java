package com.example.GenerativeAI0301.service;

import com.example.GenerativeAI0301.exception.ResourceNotFoundException;
import com.example.GenerativeAI0301.dao.ProductDao;
import com.example.GenerativeAI0301.model.Product;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService {

    @Autowired
    ProductDao productDao;

    // Retrieve all products
    public List<Product> getAllProducts() {
        return productDao.findAll();
    }

    // Retrieve a specific product by Id
    public Product getProduct(Long id) {
        return productDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", "id", id));
    }

    // Save a new product
    public Product createProduct(Product product) {
        return productDao.save(product);
    }

    // Update existing product
    public Product updateProduct(Long id, Product productDetails) {
        Product product = productDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", "id", id));
        product.setName(productDetails.getName());
        product.setDescription(productDetails.getDescription());
        // update other fields
        return productDao.save(product);
    }

    // Delete a product
    public ResponseEntity<?> deleteProduct(Long id) {
        Product product = productDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Product", "id", id));
        productDao.delete(product);

        return ResponseEntity.ok().build();
    }
}
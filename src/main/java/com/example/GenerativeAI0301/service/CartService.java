package com.example.GenerativeAI0301.service;

import com.example.GenerativeAI0301.exception.ResourceNotFoundException;
import com.example.GenerativeAI0301.dao.CartDao;
import com.example.GenerativeAI0301.model.Cart;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CartService {

    @Autowired
    CartDao cartDao;

    // Retrieve all carts
    public List<Cart> getAllCarts() {
        return cartDao.findAll();
    }

    // Retrieve a specific cart by Id
    public Cart getCart(Long id) {
        return cartDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cart", "id", id));
    }

    // Save a new cart
    public Cart createCart(Cart cart) {
        return cartDao.save(cart);
    }

    // Update existing cart
    public Cart updateCart(Long id, Cart cartDetails) {
        Cart cart = cartDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cart", "id", id));
        cart.setUser(cartDetails.getUser());
        // update other fields
        return cartDao.save(cart);
    }

    // delete a cart
    public ResponseEntity<?> deleteCart(Long id) {
        Cart cart = cartDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Cart", "id", id));
        cartDao.delete(cart);

        return ResponseEntity.ok().build();
    }
}
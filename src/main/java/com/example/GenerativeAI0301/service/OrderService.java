package com.example.GenerativeAI0301.service;

import com.example.GenerativeAI0301.exception.ResourceNotFoundException;
import com.example.GenerativeAI0301.dao.OrderDao;
import com.example.GenerativeAI0301.model.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderService {

    @Autowired
    OrderDao orderDao;

    // Retrieve all orders
    public List<Order> getAllOrders() {
        return orderDao.findAll();
    }

    // Retrieve a specific order by Id
    public Order getOrder(Long id) {
        return orderDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order", "id", id));
    }

    // Save a new order
    public Order createOrder(Order order) {
        return orderDao.save(order);
    }

    // Update existing order
    public Order updateOrder(Long id, Order orderDetails) {
        Order order = orderDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order", "id", id));
        order.setUser(orderDetails.getUser());
        order.setTotalAmount(orderDetails.getTotalAmount());
        //update other fields
        return orderDao.save(order);
    }

    // Delete an order
    public ResponseEntity<?> deleteOrder(Long id) {
        Order order = orderDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("Order", "id", id));
        orderDao.delete(order);

        return ResponseEntity.ok().build();
    }
}

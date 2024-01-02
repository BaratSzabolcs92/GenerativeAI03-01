package com.example.GenerativeAI0301.service;

import com.example.GenerativeAI0301.exception.ResourceNotFoundException;
import com.example.GenerativeAI0301.dao.UserDao;
import com.example.GenerativeAI0301.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    UserDao userDao;

    // Retrieve all users
    public List<User> getAllUsers() {
        return userDao.findAll();
    }

    // Retrieve a specific user by Id
    public User getUser(Long id) {
        return userDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
    }

    // Save a new user
    public User createUser(User user) {
        return userDao.save(user);
    }

    // Update existing user
    public User updateUser(Long id, User userDetails) {
        User user = userDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
        user.setName(userDetails.getName());
        user.setEmail(userDetails.getEmail());
        // update other fields
        return userDao.save(user);
    }

    // Delete a user
    public ResponseEntity<?> deleteUser(Long id) {
        User user = userDao.findById(id).orElseThrow(()-> new ResourceNotFoundException("User", "id", id));
        userDao.delete(user);

        return ResponseEntity.ok().build();
    }
}

package com.example.GenerativeAI0301.dao;

import com.example.GenerativeAI0301.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserDao extends JpaRepository<User, Long> { }
package com.example.demo.service.impl;

import java.util.List;

import com.example.demo.security.JwtUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dao.UserRepository;
import com.example.demo.entity.User;

@Service
public class UserService {
    private static final Logger logger =  LoggerFactory.getLogger(UserService.class);

    @Autowired
    private UserRepository userRepository;

    public User getById(int userId) {
        return userRepository.findById(userId).orElse(null);
    }

    public User getByEmail(String email) {
        return userRepository.findUserByEmail(email);
    }

    public List<User> getAll() {
        return userRepository.findAll();
    }

    public User save(User user) {
        logger.error(user.getEmail());
        return userRepository.save(user);
    }

    public void delete(int userId) {
        userRepository.deleteById(userId);
    }
}

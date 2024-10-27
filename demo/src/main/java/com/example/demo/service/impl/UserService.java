package com.example.demo.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.demo.dao.RoleRepository;
import com.example.demo.entity.Role;
import jakarta.transaction.Transactional;
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
    @Autowired
    private RoleRepository roleRepository;

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

    public User saveUserWithRoles(User user, Set<Integer> roleIds) {
        logger.error(user.getEmail());
        Set<Role> roles = new HashSet<>(roleRepository.findAllById(roleIds));
        user.setRoles(roles);
        return userRepository.save(user);
    }

    @Transactional
    public User saveUserWithNewRoles(User user, Set<Role> roles) {
        for (Role role : roles) {
            user.getRoles().add(role);
            //role.getUsers().add(user);  // Keep the relationship consistent
        }
        return userRepository.save(user);
    }

    public void delete(int userId) {
        userRepository.deleteById(userId);
    }

    @Transactional
    public boolean revokeRoleFromUser(String username, String roleName) {
        User user = userRepository.findByUsername(username).orElse(null);
        if (user == null) {
            return false;
        }

        Role role = roleRepository.findByName(roleName);
        if (role == null) {
            return false;
        }

        if (user.getRoles().contains(role)) {
            user.getRoles().remove(role);
            userRepository.save(user);
            return true;
        }

        return false;
    }

    @Transactional
    public void deleteUser(Integer userId) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Remove all category associations
        user.removeAllRoles();

        // Delete the product
        userRepository.delete(user);
    }
}

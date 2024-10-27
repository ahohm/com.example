package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.example.demo.dto.UserDTO;
import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.impl.UserService;

@RequestMapping(value = "/api/users")
@RestController
public class UserController {

    @Autowired
    private UserService userService;
    @Autowired
    private UserMapper userMapper;

    @GetMapping()
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        if (users.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
        return new ResponseEntity<>(users, HttpStatus.OK);
    }

    @GetMapping("id/{id}")
    public ResponseEntity<?> getUserByID(@PathVariable(name = "id") int userId) {
        try {
            User user = userService.getById(userId);
            if (user == null)
                return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @GetMapping("email/{email}")
    public ResponseEntity<?> getUserByEmail(@PathVariable(name = "email") String email) {
        try {
            User user = userService.getByEmail(email);
            if (user == null)
               return new ResponseEntity<>(HttpStatus.NO_CONTENT);
            else
                return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @PostMapping
    public ResponseEntity<?> saveUser(@RequestBody UserDTO userDTO) {

        try {
            //return new ResponseEntity<>(userService.save(userMapper.userDTOToUser(userDTO)), HttpStatus.OK);
            User user = userService.save(userMapper.userDTOToUser(userDTO));
            if (user == null)
                return new ResponseEntity<>(HttpStatus.NOT_ACCEPTABLE);
            else
                return new ResponseEntity<>(user, HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping
    public ResponseEntity<?> updateUser(@RequestBody UserDTO userDTO) {
        try {
            return new ResponseEntity<>(userService.save(userMapper.userDTOToUser(userDTO)), HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable(name = "id") int userDeleteId) {
        try {
            userService.delete(userDeleteId);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception) {
            return new ResponseEntity<>(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/{username}/revoke-role")
    public ResponseEntity<?> revokeRoleFromUser(@PathVariable String username, @RequestParam String roleName) {
        boolean success = userService.revokeRoleFromUser(username, roleName);
        if (success) {
            return ResponseEntity.ok("Role revoked successfully");
        } else {
            return ResponseEntity.badRequest().body("Failed to revoke role");
        }
    }
}

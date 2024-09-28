package com.fs.pos.relationalmanager.controller;

import com.fs.pos.relationalmanager.entities.User;
import com.fs.pos.relationalmanager.service.UserService;
import com.fs.pos.relationalmanager.utils.validate.StringValidator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Create a new user
    @PostMapping
    public ResponseEntity<?> createUser(@RequestBody User user) {
        // 1. Validation (secure code)
        /**
         * check username > 6 < 50 chars
         * check email, phone unique
         * check email, phone format
         */
        // validate username
        if (StringValidator.isUsernameInvalid(user.getUsername())) {
            return ResponseEntity.badRequest().body("Username must be between 6 and 50 characters");
        }

        // validate format email
        // example: hoang@email.com --> valid
        // example: hoang@email --> invalid
        if (StringValidator.isEmailInvalid(user.getEmail())) {
            return ResponseEntity.badRequest().body("Email is invalid");
        }

        // validate format phone
        // example: Vietnam 0912106550 --> valid
        // example: Vietnam 091210655 --> invalid
        // example: Vietnam +84912106550 --> valid
        // example: Singapore +56912106 --> valid
        // example: Singapore +569121065 --> invalid
        if (StringValidator.isPhoneInvalid("Vietnam", user.getPhone())) {
            return ResponseEntity.badRequest().body("Phone is invalid");
        }

        
        
        // 2. Delivery
        User createdUser = userService.createUser(user);
        return ResponseEntity.ok(createdUser);
    }

    // Get a user by ID
    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userService.getUserById(id);
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // Get all users
    @GetMapping
    public ResponseEntity<List<User>> getAllUsers() {
        List<User> users = userService.getAllUsers();
        return ResponseEntity.ok(users);
    }

    // Update a user
    @PutMapping("/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Long id, @RequestBody User userDetails) {
        User updatedUser = userService.updateUser(id, userDetails);
        return ResponseEntity.ok(updatedUser);
    }

    // Delete a user
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        userService.deleteUser(id);
        return ResponseEntity.noContent().build();
    }
}
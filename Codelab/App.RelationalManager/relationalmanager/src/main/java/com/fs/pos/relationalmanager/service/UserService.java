package com.fs.pos.relationalmanager.service;

import com.fs.pos.relationalmanager.entities.User;
import com.fs.pos.relationalmanager.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Create a new user
    public User createUser(User user) {
        // 1. Sync to LDAP
        

        // 2. Log the user creation to the audit log


        // 3. Save the user to the database
        // throw exception if user already exists
        Optional<User> existingUser = Optional.ofNullable(userRepository.findByEmail(user.getEmail()));
        if (existingUser.isPresent()) {
            // Log the user creation attempt to the audit log
            System.out.println("Attempt to create user failed. Email: " + user.getEmail() + ", User ID: " + user.getId());

            throw new RuntimeException("User with email " + user.getEmail() + " already exists");
        }

        



        return userRepository.save(user);
    }

    // Get a user by ID
    public Optional<User> getUserById(Long id) {
        return userRepository.findById(id);
    }

    // Get all users
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    /**
     * Updates the user with the specified ID using the provided user details.
     *
     * @param id the ID of the user to update.
     * @param userDetails the new details of the user.
     *            username: required
     *            phone: required
     * @return the updated user.
     * @throws RuntimeException if the user with the specified ID is not found.
     *
     * Example usage:
     * <pre>
     * {@code
     * User userDetails = new User();
     * userDetails.setUsername("newUsername");
     * userDetails.setFirstname("John");
     * userDetails.setLastname("Doe");
     * userDetails.setPhone("1234567890");
     * userDetails.setEmail("john.doe@example.com");
     * userDetails.setAge(30);
     *
     * User updatedUser = userService.updateUser(1L, userDetails);
     * }
     * </pre>
     */
    public User updateUser(Long id, User userDetails) {
        return userRepository.findById(id)
                .map(user -> {
                    user.setUsername(userDetails.getUsername());
                    user.setFirstname(userDetails.getFirstname());
                    user.setLastname(userDetails.getLastname());
                    user.setPhone(userDetails.getPhone());
                    user.setEmail(userDetails.getEmail());
                    user.setAge(userDetails.getAge());
                    return userRepository.save(user);
                })
                .orElseThrow(() -> new RuntimeException("User not found with id " + id));
    }

    // Delete a user
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
}
package com.fs.pos.relationalmanager.api;

import com.fs.pos.relationalmanager.controller.UserController;
import com.fs.pos.relationalmanager.entities.User;
import com.fs.pos.relationalmanager.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(UserController.class)
public class CreateUserTest {
    // Init an array: username input, expected output
    // build array of objecr from golden data from QC Engineer



    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    private User user;

    @BeforeEach
    void setUp() {
        user = new User();
        user.setId(1L);
        user.setFirstname("John");
        user.setLastname("Doe");
        user.setUsername("john.doe");
        user.setAge(30);
        user.setPhone("0912 106 550");
        user.setEmail("john.doe@example.com");
    }

    @Test
    void createUser() throws Exception {
        given(userService.createUser(any(User.class))).willReturn(user);

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id").value(user.getId()))
                .andExpect(jsonPath("$.username").value(user.getUsername()))
                .andExpect(jsonPath("$.email").value(user.getEmail()));
    }

    @Test
    void createUser_withEmptyUsername() throws Exception {
        user.setUsername(""); // Empty username

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username must be between 6 and 50 characters"));
    }

    @Test
    void createUser_withEmptyUsername_ContainSpace() throws Exception {
        user.setUsername("          "); // Empty username

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username must be between 6 and 50 characters"));
    }

    @Test
    void createUser_withInvalidUsername() throws Exception {
        user.setUsername("john"); // Invalid username (less than 6 characters)

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Username must be between 6 and 50 characters"));
    }

    @Test
    void createUser_withInvalidEmail() throws Exception {
        user.setEmail("john.doe@invalid"); // Invalid email format

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Email is invalid"));
    }

    @Test
    void createUser_withInvalidPhone() throws Exception {
        user.setPhone("091210655"); // Invalid phone number

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("Phone is invalid"));
    }

    @Test
    void createUser_withExistingUserbyEmail() throws Exception {
        given(userService.createUser(any(User.class))).willThrow(new RuntimeException("User with email " + user.getEmail() + " already exists"));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User with email " + user.getEmail() + " already exists"));
    }

    @Test
    void createUser_withExistingUserbyPhone() throws Exception {
        given(userService.createUser(any(User.class))).willThrow(new RuntimeException("User with email " + user.getEmail() + " already exists"));

        mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(user)))
                .andExpect(status().isBadRequest())
                .andExpect(content().string("User with email " + user.getEmail() + " already exists"));
    }
}

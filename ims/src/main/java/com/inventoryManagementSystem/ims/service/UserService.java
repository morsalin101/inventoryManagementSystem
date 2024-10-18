package com.inventoryManagementSystem.ims.service;

import com.inventoryManagementSystem.ims.model.User;
import com.inventoryManagementSystem.ims.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Optional<User> login(String email, String password) {
        Optional<User> user = userRepository.findByEmail(email);
        // Check if the user exists and if the password matches
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
}

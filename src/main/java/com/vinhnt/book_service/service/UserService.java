package com.vinhnt.book_service.service;

import com.vinhnt.book_service.dto.UserResponse;
import com.vinhnt.book_service.model.User;
import com.vinhnt.book_service.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    // Đăng ký người dùng
    public UserResponse registerUser(String email, String password, String fullName) {
        if (userRepository.findByEmail(email).isPresent()) {
            throw new IllegalArgumentException("Email is already registered");
        }

        User user = new User(email, passwordEncoder.encode(password), fullName);
        User savedUser = userRepository.save(user);

        return new UserResponse(savedUser.getId(), savedUser.getEmail(), savedUser.getFullName());
    }

    // Xác thực người dùng
    public UserResponse loginUser(String email, String password) {
        Optional<User> userOpt = userRepository.findByEmail(email);
        if (userOpt.isEmpty() || !passwordEncoder.matches(password, userOpt.get().getPassword())) {
            throw new IllegalArgumentException("Invalid email or password");
        }

        User user = userOpt.get();
        return new UserResponse(user.getId(), user.getEmail(), user.getFullName());
    }
}
package com.vinhnt.book_service.controller;

import com.vinhnt.book_service.dto.ApiResponse;
import com.vinhnt.book_service.dto.UserRequest;
import com.vinhnt.book_service.dto.UserResponse;
import com.vinhnt.book_service.model.User;
import com.vinhnt.book_service.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    // Đăng ký người dùng
    @PostMapping("/register")
    public ResponseEntity<ApiResponse<UserResponse>> registerUser(@RequestBody UserRequest userRequest) {
        UserResponse user = userService.registerUser(userRequest.getEmail(), userRequest.getPassword(), userRequest.getFullName());
        return ResponseEntity.ok(ApiResponse.success("User registered successfully", user));
    }

    // Đăng nhập
    @PostMapping("/login")
    public ResponseEntity<ApiResponse<UserResponse>> loginUser(@RequestBody UserRequest userRequest) {
        UserResponse user = userService.loginUser(userRequest.getEmail(), userRequest.getPassword());
        return ResponseEntity.ok(ApiResponse.success("Login successful", user));
    }
}
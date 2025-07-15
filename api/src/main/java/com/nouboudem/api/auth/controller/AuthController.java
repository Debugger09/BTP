package com.nouboudem.api.auth.controller;

import com.nouboudem.api.auth.dto.AuthRequest;
import com.nouboudem.api.auth.dto.AuthResponse;
import com.nouboudem.api.auth.dto.RegisterRequest;
import com.nouboudem.api.auth.service.AuthService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {
    private final AuthService authService;

    @PostMapping("/login")
    public AuthResponse login(@RequestBody AuthRequest request) {
        return authService.login(request);
    }

    @PostMapping("/register")
    public AuthResponse register(@RequestBody RegisterRequest request) {
        return authService.register(request);
    }
} 
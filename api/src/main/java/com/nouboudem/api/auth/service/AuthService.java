package com.nouboudem.api.auth.service;

import com.nouboudem.api.auth.dto.AuthRequest;
import com.nouboudem.api.auth.dto.AuthResponse;
import com.nouboudem.api.auth.dto.RegisterRequest;
import com.nouboudem.api.user.dto.UserDto;
import org.springframework.security.core.Authentication;

public interface AuthService {
    AuthResponse register(RegisterRequest request);
    AuthResponse login(AuthRequest request);
    UserDto getCurrentUser(Authentication authentication);
} 
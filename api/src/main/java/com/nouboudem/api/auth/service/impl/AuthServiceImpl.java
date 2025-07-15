package com.nouboudem.api.auth.service.impl;

import com.nouboudem.api.auth.dto.AuthRequest;
import com.nouboudem.api.auth.dto.AuthResponse;
import com.nouboudem.api.auth.service.AuthService;
import com.nouboudem.api.auth.dto.RegisterRequest;

import com.nouboudem.api.user.dto.UserDto;
import com.nouboudem.api.user.entity.Role;
import com.nouboudem.api.user.entity.User;
import com.nouboudem.api.user.mapper.UserMapper;
import com.nouboudem.api.user.repository.UserRepository;
import com.nouboudem.api.auth.security.JwtService;

import lombok.RequiredArgsConstructor;


import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;


@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserMapper userMapper;

    @Override
    public AuthResponse register(RegisterRequest request) {
        User user = User.builder()
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .statut(true)
                .nom("Admin")
                .prenom("Super")
                .build();
        userRepository.save(user);
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, userMapper.toDto(user));
    }

    @Override
    public AuthResponse login(AuthRequest request) {
        User user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new BadCredentialsException("User not found"));
        if (!passwordEncoder.matches(request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException("Invalid password");
        }
        String token = jwtService.generateToken(user);
        return new AuthResponse(token, userMapper.toDto(user));
    }

    @Override
    public UserDto getCurrentUser(Authentication authentication) {
        User user = (User) authentication.getPrincipal();
        return userMapper.toDto(user);
    }
} 
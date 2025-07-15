package com.nouboudem.api.user.service.impl;

import com.nouboudem.api.user.dto.UserDto;
import com.nouboudem.api.user.entity.User;
import com.nouboudem.api.user.entity.Role;
import com.nouboudem.api.user.mapper.UserMapper;
import com.nouboudem.api.user.repository.UserRepository;
import com.nouboudem.api.user.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public UserDto createUser(UserDto dto) {
        User user = User.builder()
                .email(dto.getEmail())
                .password(passwordEncoder.encode(dto.getPassword()))
                .nom(dto.getNom())
                .prenom(dto.getPrenom())
                .portable(dto.getPortable())
                .role(dto.getRole() != null ? dto.getRole() : Role.CLIENT)
                .statut(dto.isStatut())
                .build();
        return userMapper.toDto(userRepository.save(user));
    }

    @Override
    public UserDto getUser(Long id) {
        return userRepository.findById(id)
                .map(userMapper::toDto)
                .orElse(null);
    }

    @Override
    public List<UserDto> getAllUsers() {
        return userRepository.findAll()
                .stream()
                .map(userMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public UserDto updateUser(Long id, UserDto dto) {
        return userRepository.findById(id).map(user -> {
            user.setNom(dto.getNom());
            user.setPrenom(dto.getPrenom());
            user.setEmail(dto.getEmail());
            if (dto.getPassword() != null && !dto.getPassword().isEmpty()) {
                user.setPassword(passwordEncoder.encode(dto.getPassword()));
            }
            user.setRole(dto.getRole());
            user.setStatut(dto.isStatut());
            user.setPortable(dto.getPortable());
            return userMapper.toDto(userRepository.save(user));
        }).orElse(null);
    }

    @Override
    public void deleteUser(Long id) {
        userRepository.deleteById(id);
    }
} 
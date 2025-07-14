package com.nouboudem.api.user.service;

import com.nouboudem.api.user.dto.UserDto;
import java.util.List;

public interface UserService {
    UserDto createUser(UserDto dto);
    UserDto getUser(Long id);
    List<UserDto> getAllUsers();
    UserDto updateUser(Long id, UserDto dto);
    void deleteUser(Long id);
} 
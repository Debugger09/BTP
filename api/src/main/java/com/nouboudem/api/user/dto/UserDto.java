package com.nouboudem.api.user.dto;

import com.nouboudem.api.user.entity.Role;
import com.nouboudem.api.media.entity.Media;
import lombok.Data;

@Data
public class UserDto {
    private Long id;
    private String email;
    private String password;
    private String nom;
    private String prenom;
    private String portable;
    private Role role;
    private boolean statut;
    private Media photo;
    private Media cni;
} 
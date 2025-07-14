package com.nouboudem.api.projet.dto;

import com.nouboudem.api.user.dto.UserDto;
import com.nouboudem.api.media.entity.Media;
import lombok.Data;
import java.util.List;

@Data
public class ProjetResponseDto {
    private Long id;
    private String nom;
    private String description;
    private UserDto client;
    private List<Media> medias;
} 
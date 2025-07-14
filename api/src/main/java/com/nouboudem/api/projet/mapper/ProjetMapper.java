package com.nouboudem.api.projet.mapper;

import com.nouboudem.api.projet.entity.Projet;
import com.nouboudem.api.projet.dto.ProjetResponseDto;
import com.nouboudem.api.user.dto.UserDto;
import org.springframework.stereotype.Component;

@Component
public class ProjetMapper {
    public ProjetResponseDto toDto(Projet projet, UserDto clientDto) {
        ProjetResponseDto dto = new ProjetResponseDto();
        dto.setId(projet.getId());
        dto.setNom(projet.getNom());
        dto.setDescription(projet.getDescription());
        dto.setClient(clientDto);
        // Les médias sont à récupérer via MediaService côté controller/service
        return dto;
    }
} 
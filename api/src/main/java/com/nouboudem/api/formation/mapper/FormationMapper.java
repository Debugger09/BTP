package com.nouboudem.api.formation.mapper;

import com.nouboudem.api.formation.dto.FormationDto;
import com.nouboudem.api.formation.entity.Formation;
import org.springframework.stereotype.Component;

@Component
public class FormationMapper {
    public FormationDto toDto(Formation formation) {
        if (formation == null) return null;
        FormationDto dto = new FormationDto();
        dto.setId(formation.getId());
        dto.setTitre(formation.getTitre());
        dto.setObjectifs(formation.getObjectifs());
        dto.setDebouches(formation.getDebouches());
        dto.setDuree(formation.getDuree());
        dto.setFraisInscription(formation.getFraisInscription());
        dto.setFraisFormation(formation.getFraisFormation());
        // Les médias sont à récupérer via MediaService côté controller/service
        return dto;
    }

    public Formation toEntity(FormationDto dto) {
        if (dto == null) return null;
        Formation formation = new Formation();
        formation.setId(dto.getId());
        formation.setTitre(dto.getTitre());
        formation.setObjectifs(dto.getObjectifs());
        formation.setDebouches(dto.getDebouches());
        formation.setDuree(dto.getDuree());
        formation.setFraisInscription(dto.getFraisInscription());
        formation.setFraisFormation(dto.getFraisFormation());
        return formation;
    }
} 
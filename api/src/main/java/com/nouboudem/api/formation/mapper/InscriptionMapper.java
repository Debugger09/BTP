package com.nouboudem.api.formation.mapper;

import com.nouboudem.api.formation.dto.InscriptionDto;
import com.nouboudem.api.formation.entity.Inscription;
import org.springframework.stereotype.Component;

@Component
public class InscriptionMapper {
    public InscriptionDto toDto(Inscription inscription) {
        if (inscription == null) return null;
        InscriptionDto dto = new InscriptionDto();
        dto.setId(inscription.getId());
        dto.setNom(inscription.getNom());
        dto.setPrenom(inscription.getPrenom());
        dto.setEmail(inscription.getEmail());
        dto.setPortable(inscription.getPortable());
        dto.setDateInscription(inscription.getDateInscription());
        dto.setFormationId(inscription.getFormation() != null ? inscription.getFormation().getId() : null);
        return dto;
    }

    public Inscription toEntity(InscriptionDto dto) {
        if (dto == null) return null;
        Inscription inscription = new Inscription();
        inscription.setId(dto.getId());
        inscription.setNom(dto.getNom());
        inscription.setPrenom(dto.getPrenom());
        inscription.setEmail(dto.getEmail());
        inscription.setPortable(dto.getPortable());
        inscription.setDateInscription(dto.getDateInscription());
        // Formation à lier selon la logique métier
        return inscription;
    }
} 
package com.nouboudem.api.devis.mapper;

import com.nouboudem.api.devis.dto.*;
import com.nouboudem.api.devis.entity.*;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class DevisMapper {
    public DevisResponseDto toDto(Devis devis) {
        if (devis == null) return null;
        DevisResponseDto dto = new DevisResponseDto();
        dto.setId(devis.getId());
        dto.setType(devis.getType());
        dto.setEstPublic(devis.isEstPublic());
        dto.setProjetId(devis.getProjet() != null ? devis.getProjet().getId() : null);
        dto.setClientId(devis.getClient() != null ? devis.getClient().getId() : null);
        dto.setLots(devis.getLots() != null ? devis.getLots().stream().map(this::toDto).collect(Collectors.toList()) : null);
        // Les lignes hors lot
        dto.setLignes(devis.getLots() != null ? devis.getLots().stream()
            .flatMap(lot -> lot.getLignesDevis().stream())
            .filter(ligne -> ligne.getLotTravaux() == null)
            .map(this::toDto)
            .collect(Collectors.toList()) : null);
        // Les médias sont à ajouter côté service/controller
        return dto;
    }

    public LotTravauxDto toDto(LotTravaux lot) {
        if (lot == null) return null;
        LotTravauxDto dto = new LotTravauxDto();
        dto.setId(lot.getId());
        dto.setNom(lot.getNom());
        dto.setLignes(lot.getLignesDevis() != null ? lot.getLignesDevis().stream().map(this::toDto).collect(Collectors.toList()) : null);
        return dto;
    }

    public LigneDevisDto toDto(LigneDevis ligne) {
        if (ligne == null) return null;
        LigneDevisDto dto = new LigneDevisDto();
        dto.setId(ligne.getId());
        dto.setDescription(ligne.getDescription());
        dto.setUnite(ligne.getUnite());
        dto.setQuantite(ligne.getQuantite());
        dto.setPrixUnitaire(ligne.getPrixUnitaire());
        dto.setMontant(ligne.getMontant());
        dto.setLotTravauxId(ligne.getLotTravaux() != null ? ligne.getLotTravaux().getId() : null);
        dto.setDevisId(ligne.getDevis() != null ? ligne.getDevis().getId() : null);
        return dto;
    }
} 
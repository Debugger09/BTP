package com.nouboudem.api.projet.mapper;

import com.nouboudem.api.projet.dto.*;
import com.nouboudem.api.projet.entity.*;
import org.springframework.stereotype.Component;
import java.util.stream.Collectors;

@Component
public class PhasePaiementMapper {
    public PhaseDto toDto(Phase phase) {
        if (phase == null) return null;
        PhaseDto dto = new PhaseDto();
        dto.setId(phase.getId());
        dto.setTitre(phase.getTitre());
        dto.setOrdre(phase.getOrdre());
        dto.setProjetId(phase.getProjet() != null ? phase.getProjet().getId() : null);
        dto.setPaiements(phase.getPaiements() != null ? phase.getPaiements().stream().map(this::toDto).collect(Collectors.toList()) : null);
        return dto;
    }

    public PaiementDto toDto(Paiement paiement) {
        if (paiement == null) return null;
        PaiementDto dto = new PaiementDto();
        dto.setId(paiement.getId());
        dto.setPhaseId(paiement.getPhase() != null ? paiement.getPhase().getId() : null);
        dto.setMontant(paiement.getMontant());
        dto.setDatePaiement(paiement.getDatePaiement());
        dto.setModePaiement(paiement.getModePaiement());
        // Les médias sont à ajouter côté service/controller
        return dto;
    }
} 
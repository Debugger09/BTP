package com.nouboudem.api.projet.service;

import com.nouboudem.api.projet.dto.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface PhasePaiementService {
    // PHASES
    PhaseDto createPhase(Long projetId, PhaseDto dto);
    PhaseDto getPhase(Long id);
    List<PhaseDto> getPhasesByProjet(Long projetId);
    PhaseDto updatePhase(Long id, PhaseDto dto);
    void deletePhase(Long id);

    // PAIEMENTS
    PaiementDto createPaiement(Long phaseId, PaiementDto dto);
    PaiementDto getPaiement(Long id);
    List<PaiementDto> getPaiementsByPhase(Long phaseId);
    PaiementDto updatePaiement(Long id, PaiementDto dto);
    void deletePaiement(Long id);

    // Médias (preuves)
    void addMediaToPaiement(Long paiementId, MultipartFile file, String description) throws IOException;
} 
package com.nouboudem.api.projet.service.impl;

import com.nouboudem.api.media.entity.EntityType;
import com.nouboudem.api.media.service.MediaService;
import com.nouboudem.api.projet.dto.*;
import com.nouboudem.api.projet.entity.*;
import com.nouboudem.api.projet.mapper.PhasePaiementMapper;
import com.nouboudem.api.projet.repository.*;
import com.nouboudem.api.projet.service.PhasePaiementService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class PhasePaiementServiceImpl implements PhasePaiementService {
    private final PhaseRepository phaseRepository;
    private final PaiementRepository paiementRepository;
    private final ProjetRepository projetRepository;
    private final MediaService mediaService;
    private final PhasePaiementMapper mapper;

    // PHASES
    @Override
    public PhaseDto createPhase(Long projetId, PhaseDto dto) {
        Projet projet = projetRepository.findById(projetId).orElseThrow();
        Phase phase = new Phase();
        phase.setTitre(dto.getTitre());
        phase.setOrdre(dto.getOrdre());
        phase.setProjet(projet);
        phase = phaseRepository.save(phase);
        return mapper.toDto(phase);
    }

    @Override
    public PhaseDto getPhase(Long id) {
        return phaseRepository.findById(id).map(mapper::toDto).orElse(null);
    }

    @Override
    public List<PhaseDto> getPhasesByProjet(Long projetId) {
        return phaseRepository.findAll().stream()
                .filter(phase -> phase.getProjet() != null && phase.getProjet().getId().equals(projetId))
                .map(mapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public PhaseDto updatePhase(Long id, PhaseDto dto) {
        return phaseRepository.findById(id).map(phase -> {
            phase.setTitre(dto.getTitre());
            phase.setOrdre(dto.getOrdre());
            phase = phaseRepository.save(phase);
            return mapper.toDto(phase);
        }).orElse(null);
    }

    @Override
    public void deletePhase(Long id) {
        phaseRepository.deleteById(id);
    }

    // PAIEMENTS
    @Override
    public PaiementDto createPaiement(Long phaseId, PaiementDto dto) {
        Phase phase = phaseRepository.findById(phaseId).orElseThrow();
        Paiement paiement = new Paiement();
        paiement.setPhase(phase);
        paiement.setMontant(dto.getMontant());
        paiement.setDatePaiement(dto.getDatePaiement());
        paiement.setModePaiement(dto.getModePaiement());
        paiement = paiementRepository.save(paiement);
        PaiementDto result = mapper.toDto(paiement);
        result.setMedias(mediaService.getMediaForEntity(EntityType.PAIEMENT, paiement.getId()));
        return result;
    }

    @Override
    public PaiementDto getPaiement(Long id) {
        return paiementRepository.findById(id).map(paiement -> {
            PaiementDto dto = mapper.toDto(paiement);
            dto.setMedias(mediaService.getMediaForEntity(EntityType.PAIEMENT, paiement.getId()));
            return dto;
        }).orElse(null);
    }

    @Override
    public List<PaiementDto> getPaiementsByPhase(Long phaseId) {
        return paiementRepository.findAll().stream()
                .filter(p -> p.getPhase() != null && p.getPhase().getId().equals(phaseId))
                .map(paiement -> {
                    PaiementDto dto = mapper.toDto(paiement);
                    dto.setMedias(mediaService.getMediaForEntity(EntityType.PAIEMENT, paiement.getId()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public PaiementDto updatePaiement(Long id, PaiementDto dto) {
        return paiementRepository.findById(id).map(paiement -> {
            paiement.setMontant(dto.getMontant());
            paiement.setDatePaiement(dto.getDatePaiement());
            paiement.setModePaiement(dto.getModePaiement());
            paiement = paiementRepository.save(paiement);
            PaiementDto result = mapper.toDto(paiement);
            result.setMedias(mediaService.getMediaForEntity(EntityType.PAIEMENT, paiement.getId()));
            return result;
        }).orElse(null);
    }

    @Override
    public void deletePaiement(Long id) {
        paiementRepository.deleteById(id);
    }

    // Médias (preuves)
    @Override
    public void addMediaToPaiement(Long paiementId, MultipartFile file, String description) throws IOException {
        mediaService.uploadMedia(EntityType.PAIEMENT, paiementId, file, description);
    }
} 
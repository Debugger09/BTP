package com.nouboudem.api.projet.controller;

import com.nouboudem.api.projet.dto.*;
import com.nouboudem.api.projet.service.PhasePaiementService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/phases")
@RequiredArgsConstructor
public class PhasePaiementController {
    private final PhasePaiementService phasePaiementService;

    // PHASES
    @PostMapping("/projet/{projetId}")
    public PhaseDto createPhase(@PathVariable Long projetId, @RequestBody PhaseDto dto) {
        return phasePaiementService.createPhase(projetId, dto);
    }

    @GetMapping("/{id}")
    public PhaseDto getPhase(@PathVariable Long id) {
        return phasePaiementService.getPhase(id);
    }

    @GetMapping("/projet/{projetId}")
    public List<PhaseDto> getPhasesByProjet(@PathVariable Long projetId) {
        return phasePaiementService.getPhasesByProjet(projetId);
    }

    @PutMapping("/{id}")
    public PhaseDto updatePhase(@PathVariable Long id, @RequestBody PhaseDto dto) {
        return phasePaiementService.updatePhase(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deletePhase(@PathVariable Long id) {
        phasePaiementService.deletePhase(id);
    }

    // PAIEMENTS
    @PostMapping("/{phaseId}/paiements")
    public PaiementDto createPaiement(@PathVariable Long phaseId, @RequestBody PaiementDto dto) {
        return phasePaiementService.createPaiement(phaseId, dto);
    }

    @GetMapping("/paiements/{id}")
    public PaiementDto getPaiement(@PathVariable Long id) {
        return phasePaiementService.getPaiement(id);
    }

    @GetMapping("/{phaseId}/paiements")
    public List<PaiementDto> getPaiementsByPhase(@PathVariable Long phaseId) {
        return phasePaiementService.getPaiementsByPhase(phaseId);
    }

    @PutMapping("/paiements/{id}")
    public PaiementDto updatePaiement(@PathVariable Long id, @RequestBody PaiementDto dto) {
        return phasePaiementService.updatePaiement(id, dto);
    }

    @DeleteMapping("/paiements/{id}")
    public void deletePaiement(@PathVariable Long id) {
        phasePaiementService.deletePaiement(id);
    }

    // Médias (preuves de paiement)
    @PostMapping(value = "/paiements/{paiementId}/media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addMediaToPaiement(
            @PathVariable Long paiementId,
            @RequestPart MultipartFile file,
            @RequestPart(required = false) String description
    ) throws IOException {
        phasePaiementService.addMediaToPaiement(paiementId, file, description);
    }
} 
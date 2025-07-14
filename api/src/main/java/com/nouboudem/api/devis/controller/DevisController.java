package com.nouboudem.api.devis.controller;

import com.nouboudem.api.devis.dto.*;
import com.nouboudem.api.devis.service.DevisService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/devis")
@RequiredArgsConstructor
public class DevisController {
    private final DevisService devisService;

    @PostMapping
    public DevisResponseDto createDevis(@RequestBody DevisRequestDto dto) {
        return devisService.createDevis(dto);
    }

    @GetMapping
    public List<DevisResponseDto> getAllDevis() {
        return devisService.getAllDevis();
    }

    @GetMapping("/{id}")
    public DevisResponseDto getDevis(@PathVariable Long id) {
        return devisService.getDevis(id);
    }

    @PutMapping("/{id}")
    public DevisResponseDto updateDevis(@PathVariable Long id, @RequestBody DevisRequestDto dto) {
        return devisService.updateDevis(id, dto);
    }

    @DeleteMapping("/{id}")
    public void deleteDevis(@PathVariable Long id) {
        devisService.deleteDevis(id);
    }

    @PostMapping("/{id}/lots")
    public LotTravauxDto addLotToDevis(@PathVariable Long id, @RequestBody LotTravauxDto lotDto) {
        return devisService.addLotToDevis(id, lotDto);
    }

    @PostMapping("/{id}/lignes")
    public LigneDevisDto addLigneToDevis(@PathVariable Long id, @RequestBody LigneDevisDto ligneDto) {
        return devisService.addLigneToDevis(id, ligneDto);
    }

    @PostMapping("/lots/{lotId}/lignes")
    public LigneDevisDto addLigneToLot(@PathVariable Long lotId, @RequestBody LigneDevisDto ligneDto) {
        return devisService.addLigneToLot(lotId, ligneDto);
    }

    @PutMapping("/lots/{lotId}")
    public LotTravauxDto updateLot(@PathVariable Long lotId, @RequestBody LotTravauxDto lotDto) {
        return devisService.updateLot(lotId, lotDto);
    }

    @DeleteMapping("/lots/{lotId}")
    public void deleteLot(@PathVariable Long lotId) {
        devisService.deleteLot(lotId);
    }

    @PutMapping("/lignes/{ligneId}")
    public LigneDevisDto updateLigne(@PathVariable Long ligneId, @RequestBody LigneDevisDto ligneDto) {
        return devisService.updateLigne(ligneId, ligneDto);
    }

    @DeleteMapping("/lignes/{ligneId}")
    public void deleteLigne(@PathVariable Long ligneId) {
        devisService.deleteLigne(ligneId);
    }

    @PostMapping(value = "/{id}/media", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public void addMediaToDevis(
            @PathVariable Long id,
            @RequestPart MultipartFile file,
            @RequestPart(required = false) String description
    ) throws IOException {
        devisService.addMediaToDevis(id, file, description);
    }
} 
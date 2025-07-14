package com.nouboudem.api.temoignage.controller;

import com.nouboudem.api.temoignage.dto.TemoignageDto;
import com.nouboudem.api.temoignage.dto.TemoignageCreateDto;
import com.nouboudem.api.temoignage.service.TemoignageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/temoignages")
@RequiredArgsConstructor
public class TemoignageController {
    private final TemoignageService temoignageService;

    // Liste publique (frontend)
    @GetMapping("/public")
    public List<TemoignageDto> getTemoignagesPublies() {
        return temoignageService.getTemoignagesPublies();
    }

    // Liste complète (admin)
    @GetMapping
    public List<TemoignageDto> getAllTemoignages() {
        return temoignageService.getAllTemoignages();
    }

    @GetMapping("/{id}")
    public ResponseEntity<TemoignageDto> getTemoignage(@PathVariable Long id) {
        TemoignageDto dto = temoignageService.getTemoignage(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public TemoignageDto createTemoignage(@RequestBody TemoignageCreateDto dto) {
        return temoignageService.createTemoignage(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<TemoignageDto> updateTemoignage(@PathVariable Long id, @RequestBody TemoignageCreateDto dto) {
        TemoignageDto updated = temoignageService.updateTemoignage(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTemoignage(@PathVariable Long id) {
        temoignageService.deleteTemoignage(id);
        return ResponseEntity.noContent().build();
    }

    // Publier / Dépublier (admin)
    @PatchMapping("/{id}/publier")
    public ResponseEntity<TemoignageDto> publierTemoignage(@PathVariable Long id, @RequestParam boolean publier) {
        TemoignageDto dto = temoignageService.publierTemoignage(id, publier);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    // Liste par utilisateur (optionnel)
    @GetMapping("/user/{userId}")
    public List<TemoignageDto> getTemoignagesByUser(@PathVariable Long userId) {
        return temoignageService.getTemoignagesByUser(userId);
    }
} 
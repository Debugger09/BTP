package com.nouboudem.api.formation.controller;

import com.nouboudem.api.formation.dto.InscriptionDto;
import com.nouboudem.api.formation.service.InscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/inscriptions")
public class InscriptionController {
    @Autowired
    private InscriptionService inscriptionService;

    @GetMapping
    public List<InscriptionDto> getAllInscriptions() {
        return inscriptionService.getAllInscriptions();
    }

    @GetMapping("/{id}")
    public InscriptionDto getInscriptionById(@PathVariable Long id) {
        return inscriptionService.getInscriptionById(id);
    }

    @PostMapping
    public InscriptionDto createInscription(@RequestBody InscriptionDto inscriptionDto) {
        return inscriptionService.createInscription(inscriptionDto);
    }

    @PutMapping("/{id}")
    public InscriptionDto updateInscription(@PathVariable Long id, @RequestBody InscriptionDto inscriptionDto) {
        return inscriptionService.updateInscription(id, inscriptionDto);
    }

    @DeleteMapping("/{id}")
    public void deleteInscription(@PathVariable Long id) {
        inscriptionService.deleteInscription(id);
    }
}

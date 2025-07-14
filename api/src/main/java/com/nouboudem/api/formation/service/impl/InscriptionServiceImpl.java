package com.nouboudem.api.formation.service.impl;

import com.nouboudem.api.formation.dto.InscriptionDto;
import com.nouboudem.api.formation.entity.Inscription;
import com.nouboudem.api.formation.mapper.InscriptionMapper;
import com.nouboudem.api.formation.repository.InscriptionRepository;
import com.nouboudem.api.formation.service.InscriptionService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class InscriptionServiceImpl implements InscriptionService {
    private final InscriptionRepository inscriptionRepository;
    private final InscriptionMapper inscriptionMapper;

    @Override
    public List<InscriptionDto> getAllInscriptions() {
        return inscriptionRepository.findAll().stream()
                .map(inscriptionMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public InscriptionDto getInscriptionById(Long id) {
        return inscriptionRepository.findById(id)
                .map(inscriptionMapper::toDto)
                .orElse(null);
    }

    @Override
    public InscriptionDto createInscription(InscriptionDto inscriptionDto) {
        Inscription inscription = inscriptionMapper.toEntity(inscriptionDto);
        return inscriptionMapper.toDto(inscriptionRepository.save(inscription));
    }

    @Override
    public InscriptionDto updateInscription(Long id, InscriptionDto inscriptionDto) {
        return inscriptionRepository.findById(id).map(inscription -> {
            inscription.setNom(inscriptionDto.getNom());
            inscription.setPrenom(inscriptionDto.getPrenom());
            inscription.setEmail(inscriptionDto.getEmail());
            inscription.setPortable(inscriptionDto.getPortable());
            // Ajoute d'autres champs à mettre à jour si besoin
            return inscriptionMapper.toDto(inscriptionRepository.save(inscription));
        }).orElse(null);
    }

    @Override
    public void deleteInscription(Long id) {
        inscriptionRepository.deleteById(id);
    }
} 
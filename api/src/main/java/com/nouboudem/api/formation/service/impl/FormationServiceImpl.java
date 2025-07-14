package com.nouboudem.api.formation.service.impl;

import com.nouboudem.api.formation.dto.FormationDto;
import com.nouboudem.api.formation.entity.Formation;
import com.nouboudem.api.formation.mapper.FormationMapper;
import com.nouboudem.api.formation.repository.FormationRepository;
import com.nouboudem.api.formation.service.FormationService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class FormationServiceImpl implements FormationService {
    private final FormationRepository formationRepository;
    private final FormationMapper formationMapper;

    @Override
    public List<FormationDto> getAllFormations() {
        return formationRepository.findAll().stream()
                .map(formationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public FormationDto getFormationById(Long id) {
        return formationRepository.findById(id)
                .map(formationMapper::toDto)
                .orElse(null);
    }

    @Override
    public FormationDto createFormation(FormationDto formationDto) {
        Formation formation = formationMapper.toEntity(formationDto);
        return formationMapper.toDto(formationRepository.save(formation));
    }

    @Override
    public FormationDto updateFormation(Long id, FormationDto formationDto) {
        return formationRepository.findById(id).map(formation -> {
            formation.setTitre(formationDto.getTitre());
            formation.setObjectifs(formationDto.getObjectifs());
            formation.setDebouches(formationDto.getDebouches());
            formation.setDuree(formationDto.getDuree());
            formation.setFraisInscription(formationDto.getFraisInscription());
            formation.setFraisFormation(formationDto.getFraisFormation());
            return formationMapper.toDto(formationRepository.save(formation));
        }).orElse(null);
    }

    @Override
    public void deleteFormation(Long id) {
        formationRepository.deleteById(id);
    }
} 
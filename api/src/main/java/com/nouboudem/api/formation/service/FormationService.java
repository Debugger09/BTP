package com.nouboudem.api.formation.service;

import com.nouboudem.api.formation.dto.FormationDto;
import java.util.List;

public interface FormationService {
    List<FormationDto> getAllFormations();
    FormationDto getFormationById(Long id);
    FormationDto createFormation(FormationDto formationDto);
    FormationDto updateFormation(Long id, FormationDto formationDto);
    void deleteFormation(Long id);
} 
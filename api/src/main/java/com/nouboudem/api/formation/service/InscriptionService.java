package com.nouboudem.api.formation.service;

import com.nouboudem.api.formation.dto.InscriptionDto;
import java.util.List;

public interface InscriptionService {
    List<InscriptionDto> getAllInscriptions();
    InscriptionDto getInscriptionById(Long id);
    InscriptionDto createInscription(InscriptionDto inscriptionDto);
    InscriptionDto updateInscription(Long id, InscriptionDto inscriptionDto);
    void deleteInscription(Long id);
} 
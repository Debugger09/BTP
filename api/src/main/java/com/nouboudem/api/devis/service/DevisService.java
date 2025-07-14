package com.nouboudem.api.devis.service;

import com.nouboudem.api.devis.dto.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface DevisService {
    DevisResponseDto createDevis(DevisRequestDto dto);
    DevisResponseDto getDevis(Long id);
    List<DevisResponseDto> getAllDevis();
    DevisResponseDto updateDevis(Long id, DevisRequestDto dto);
    void deleteDevis(Long id);

    LotTravauxDto addLotToDevis(Long devisId, LotTravauxDto lotDto);
    LigneDevisDto addLigneToDevis(Long devisId, LigneDevisDto ligneDto);
    LigneDevisDto addLigneToLot(Long lotId, LigneDevisDto ligneDto);

    void addMediaToDevis(Long devisId, MultipartFile file, String description) throws IOException;
    LotTravauxDto updateLot(Long lotId, LotTravauxDto lotDto);
    void deleteLot(Long lotId);
    LigneDevisDto updateLigne(Long ligneId, LigneDevisDto ligneDto);
    void deleteLigne(Long ligneId);
} 
package com.nouboudem.api.temoignage.service;

import com.nouboudem.api.temoignage.dto.TemoignageDto;
import com.nouboudem.api.temoignage.dto.TemoignageCreateDto;
import java.util.List;

public interface TemoignageService {
    List<TemoignageDto> getAllTemoignages(); // admin
    List<TemoignageDto> getTemoignagesPublies(); // public
    TemoignageDto getTemoignage(Long id);
    TemoignageDto createTemoignage(TemoignageCreateDto dto);
    TemoignageDto updateTemoignage(Long id, TemoignageCreateDto dto);
    void deleteTemoignage(Long id);
    TemoignageDto publierTemoignage(Long id, boolean publier);
    List<TemoignageDto> getTemoignagesByUser(Long userId);
} 
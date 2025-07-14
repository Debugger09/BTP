package com.nouboudem.api.devis.service.impl;

import com.nouboudem.api.devis.dto.*;
import com.nouboudem.api.devis.entity.*;
import com.nouboudem.api.devis.mapper.DevisMapper;
import com.nouboudem.api.devis.repository.*;
import com.nouboudem.api.devis.service.DevisService;
import com.nouboudem.api.media.entity.EntityType;
import com.nouboudem.api.media.service.MediaService;
import com.nouboudem.api.projet.entity.Projet;
import com.nouboudem.api.projet.repository.ProjetRepository;
import com.nouboudem.api.user.entity.User;
import com.nouboudem.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class DevisServiceImpl implements DevisService {
    private final DevisRepository devisRepository;
    private final LotTravauxRepository lotTravauxRepository;
    private final LigneDevisRepository ligneDevisRepository;
    private final ProjetRepository projetRepository;
    private final UserRepository userRepository;
    private final MediaService mediaService;
    private final DevisMapper devisMapper;

    @Override
    public DevisResponseDto createDevis(DevisRequestDto dto) {
        Devis devis = new Devis();
        devis.setType(dto.getType());
        devis.setEstPublic(dto.isEstPublic());
        devis.setObjet(dto.getObjet());
        devis.setTotalHt(dto.getTotalHt());
        devis.setTva(dto.getTva());
        devis.setTotalTtc(dto.getTotalTtc());
        if (dto.getProjetId() != null) {
            Projet projet = projetRepository.findById(dto.getProjetId()).orElse(null);
            devis.setProjet(projet);
        }
        if (dto.getClientId() != null) {
            User client = userRepository.findById(dto.getClientId()).orElse(null);
            devis.setClient(client);
        }
        devis = devisRepository.save(devis);
        DevisResponseDto response = devisMapper.toDto(devis);
        response.setMedias(mediaService.getMediaForEntity(EntityType.DEVIS, devis.getId()));
        return response;
    }

    @Override
    public DevisResponseDto getDevis(Long id) {
        return devisRepository.findById(id)
                .map(devis -> {
                    DevisResponseDto dto = devisMapper.toDto(devis);
                    dto.setMedias(mediaService.getMediaForEntity(EntityType.DEVIS, devis.getId()));
                    return dto;
                })
                .orElse(null);
    }

    @Override
    public List<DevisResponseDto> getAllDevis() {
        return devisRepository.findAll().stream()
                .map(devis -> {
                    DevisResponseDto dto = devisMapper.toDto(devis);
                    dto.setMedias(mediaService.getMediaForEntity(EntityType.DEVIS, devis.getId()));
                    return dto;
                })
                .collect(Collectors.toList());
    }

    @Override
    public DevisResponseDto updateDevis(Long id, DevisRequestDto dto) {
        return devisRepository.findById(id).map(devis -> {
            devis.setType(dto.getType());
            devis.setEstPublic(dto.isEstPublic());
            devis.setObjet(dto.getObjet());
            devis.setTotalHt(dto.getTotalHt());
            devis.setTva(dto.getTva());
            devis.setTotalTtc(dto.getTotalTtc());
            if (dto.getProjetId() != null) {
                Projet projet = projetRepository.findById(dto.getProjetId()).orElse(null);
                devis.setProjet(projet);
            }
            if (dto.getClientId() != null) {
                User client = userRepository.findById(dto.getClientId()).orElse(null);
                devis.setClient(client);
            }
            devis = devisRepository.save(devis);
            DevisResponseDto response = devisMapper.toDto(devis);
            response.setMedias(mediaService.getMediaForEntity(EntityType.DEVIS, devis.getId()));
            return response;
        }).orElse(null);
    }

    @Override
    public void deleteDevis(Long id) {
        devisRepository.deleteById(id);
    }

    @Override
    public LotTravauxDto addLotToDevis(Long devisId, LotTravauxDto lotDto) {
        Devis devis = devisRepository.findById(devisId).orElseThrow();
        LotTravaux lot = new LotTravaux();
        lot.setNom(lotDto.getNom());
        lot.setDevis(devis);
        lot = lotTravauxRepository.save(lot);
        return devisMapper.toDto(lot);
    }

    @Override
    public LigneDevisDto addLigneToDevis(Long devisId, LigneDevisDto ligneDto) {
        Devis devis = devisRepository.findById(devisId).orElseThrow();
        LigneDevis ligne = new LigneDevis();
        ligne.setDescription(ligneDto.getDescription());
        ligne.setUnite(ligneDto.getUnite());
        ligne.setQuantite(ligneDto.getQuantite());
        ligne.setPrixUnitaire(ligneDto.getPrixUnitaire());
        ligne.setMontant(ligneDto.getMontant());
        ligne.setDevis(devis);
        ligne = ligneDevisRepository.save(ligne);
        return devisMapper.toDto(ligne);
    }

    @Override
    public LigneDevisDto addLigneToLot(Long lotId, LigneDevisDto ligneDto) {
        LotTravaux lot = lotTravauxRepository.findById(lotId).orElseThrow();
        LigneDevis ligne = new LigneDevis();
        ligne.setDescription(ligneDto.getDescription());
        ligne.setUnite(ligneDto.getUnite());
        ligne.setQuantite(ligneDto.getQuantite());
        ligne.setPrixUnitaire(ligneDto.getPrixUnitaire());
        ligne.setMontant(ligneDto.getMontant());
        ligne.setLotTravaux(lot);
        ligne.setDevis(lot.getDevis());
        ligne = ligneDevisRepository.save(ligne);
        return devisMapper.toDto(ligne);
    }

    @Override
    public void addMediaToDevis(Long devisId, MultipartFile file, String description) throws IOException {
        mediaService.uploadMedia(EntityType.DEVIS, devisId, file, description);
    }

    @Override
    public LotTravauxDto updateLot(Long lotId, LotTravauxDto lotDto) {
        return lotTravauxRepository.findById(lotId).map(lot -> {
            lot.setNom(lotDto.getNom());
            lot = lotTravauxRepository.save(lot);
            return devisMapper.toDto(lot);
        }).orElse(null);
    }

    @Override
    public void deleteLot(Long lotId) {
        lotTravauxRepository.deleteById(lotId);
    }

    @Override
    public LigneDevisDto updateLigne(Long ligneId, LigneDevisDto ligneDto) {
        return ligneDevisRepository.findById(ligneId).map(ligne -> {
            ligne.setDescription(ligneDto.getDescription());
            ligne.setUnite(ligneDto.getUnite());
            ligne.setQuantite(ligneDto.getQuantite());
            ligne.setPrixUnitaire(ligneDto.getPrixUnitaire());
            ligne.setMontant(ligneDto.getMontant());
            ligne = ligneDevisRepository.save(ligne);
            return devisMapper.toDto(ligne);
        }).orElse(null);
    }

    @Override
    public void deleteLigne(Long ligneId) {
        ligneDevisRepository.deleteById(ligneId);
    }
} 
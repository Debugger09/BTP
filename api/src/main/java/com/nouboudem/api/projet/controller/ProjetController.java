package com.nouboudem.api.projet.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nouboudem.api.media.entity.EntityType;

import com.nouboudem.api.media.service.MediaService;
import com.nouboudem.api.projet.dto.ProjetRequestDto;
import com.nouboudem.api.projet.dto.ProjetResponseDto;
import com.nouboudem.api.projet.entity.Projet;
import com.nouboudem.api.projet.mapper.ProjetMapper;
import com.nouboudem.api.projet.repository.ProjetRepository;
import com.nouboudem.api.user.entity.User;
import com.nouboudem.api.user.mapper.UserMapper;
import com.nouboudem.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/projets")
@RequiredArgsConstructor
public class ProjetController {
    private final ProjetRepository projetRepository;
    private final UserRepository userRepository;
    private final ProjetMapper projetMapper;
    private final UserMapper userMapper;
    private final MediaService mediaService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProjetResponseDto createProjetWithFiles(
            @RequestPart("projet") String projetJson,
            @RequestPart(value = "medias", required = false) List<MultipartFile> medias
    ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        ProjetRequestDto projetDto = mapper.readValue(projetJson, ProjetRequestDto.class);

        User client = userRepository.findById(projetDto.getClientId()).orElseThrow();
        Projet projet = new Projet();
        projet.setNom(projetDto.getNom());
        projet.setDescription(projetDto.getDescription());
        projet.setClient(client);
        projet = projetRepository.save(projet);

        // Upload des médias (images/fichiers)
        if (medias != null) {
            for (MultipartFile file : medias) {
                mediaService.uploadMedia(EntityType.PROJET, projet.getId(), file, null);
            }
        }
        ProjetResponseDto dto = projetMapper.toDto(projet, userMapper.toDto(projet.getClient()));
        dto.setMedias(mediaService.getMediaForEntity(EntityType.PROJET, projet.getId()));
        return dto;
    }

    @GetMapping
    public List<ProjetResponseDto> getAllProjets() {
        List<Projet> projets = projetRepository.findAll();
        List<ProjetResponseDto> dtos = new ArrayList<>();
        for (Projet projet : projets) {
            ProjetResponseDto dto = projetMapper.toDto(projet, userMapper.toDto(projet.getClient()));
            dto.setMedias(mediaService.getMediaForEntity(EntityType.PROJET, projet.getId()));
            dtos.add(dto);
        }
        return dtos;
    }

    @GetMapping("/{id}")
    public ProjetResponseDto getProjetById(@PathVariable Long id) {
        Projet projet = projetRepository.findById(id).orElseThrow();
        ProjetResponseDto dto = projetMapper.toDto(projet, userMapper.toDto(projet.getClient()));
        dto.setMedias(mediaService.getMediaForEntity(EntityType.PROJET, projet.getId()));
        return dto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ProjetResponseDto updateProjet(
            @PathVariable Long id,
            @RequestPart("projet") String projetJson,
            @RequestPart(value = "medias", required = false) List<MultipartFile> medias
    ) throws IOException {
        Projet projet = projetRepository.findById(id).orElseThrow();
        ObjectMapper mapper = new ObjectMapper();
        ProjetRequestDto projetDto = mapper.readValue(projetJson, ProjetRequestDto.class);
        projet.setNom(projetDto.getNom());
        projet.setDescription(projetDto.getDescription());
        if (projetDto.getClientId() != null) {
            User client = userRepository.findById(projetDto.getClientId()).orElseThrow();
            projet.setClient(client);
        }
        projet = projetRepository.save(projet);
        // Upload nouveaux médias si fournis
        if (medias != null) {
            for (MultipartFile file : medias) {
                mediaService.uploadMedia(EntityType.PROJET, projet.getId(), file, null);
            }
        }
        ProjetResponseDto dto = projetMapper.toDto(projet, userMapper.toDto(projet.getClient()));
        dto.setMedias(mediaService.getMediaForEntity(EntityType.PROJET, projet.getId()));
        return dto;
    }

    @DeleteMapping("/{id}")
    public void deleteProjet(@PathVariable Long id) {
        projetRepository.deleteById(id);
    }
} 
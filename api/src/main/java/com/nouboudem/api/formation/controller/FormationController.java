package com.nouboudem.api.formation.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.nouboudem.api.formation.dto.FormationDto;
import com.nouboudem.api.formation.service.FormationService;
import com.nouboudem.api.media.entity.EntityType;
import com.nouboudem.api.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/formations")
@RequiredArgsConstructor
public class FormationController {
    private final FormationService formationService;
    private final MediaService mediaService;

    @PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FormationDto createFormationWithMedias(
            @RequestPart("formation") String formationJson,
            @RequestPart(value = "medias", required = false) List<MultipartFile> medias
    ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FormationDto formationDto = mapper.readValue(formationJson, FormationDto.class);
        FormationDto created = formationService.createFormation(formationDto);
        if (medias != null && created != null && created.getId() != null) {
            for (MultipartFile file : medias) {
                mediaService.uploadMedia(EntityType.FORMATION, created.getId(), file, null);
            }
            created.setMedias(mediaService.getMediaForEntity(EntityType.FORMATION, created.getId()));
        }
        return created;
    }

    @GetMapping
    public List<FormationDto> getAllFormations() {
        List<FormationDto> dtos = formationService.getAllFormations();
        for (FormationDto dto : dtos) {
            if (dto.getId() != null) {
                dto.setMedias(mediaService.getMediaForEntity(EntityType.FORMATION, dto.getId()));
            }
        }
        return dtos;
    }

    @GetMapping("/{id}")
    public FormationDto getFormationById(@PathVariable Long id) {
        FormationDto dto = formationService.getFormationById(id);
        if (dto != null && dto.getId() != null) {
            dto.setMedias(mediaService.getMediaForEntity(EntityType.FORMATION, dto.getId()));
        }
        return dto;
    }

    @PutMapping(value = "/{id}", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public FormationDto updateFormation(
            @PathVariable Long id,
            @RequestPart("formation") String formationJson,
            @RequestPart(value = "medias", required = false) List<MultipartFile> medias
    ) throws IOException {
        ObjectMapper mapper = new ObjectMapper();
        FormationDto formationDto = mapper.readValue(formationJson, FormationDto.class);
        FormationDto updated = formationService.updateFormation(id, formationDto);
        if (medias != null && updated != null && updated.getId() != null) {
            for (MultipartFile file : medias) {
                mediaService.uploadMedia(EntityType.FORMATION, updated.getId(), file, null);
            }
            updated.setMedias(mediaService.getMediaForEntity(EntityType.FORMATION, updated.getId()));
        }
        return updated;
    }

    @DeleteMapping("/{id}")
    public void deleteFormation(@PathVariable Long id) {
        formationService.deleteFormation(id);
    }
}

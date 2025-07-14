package com.nouboudem.api.media.controller;

import com.nouboudem.api.media.entity.Media;
import com.nouboudem.api.media.entity.EntityType;
import com.nouboudem.api.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/api/media")
@RequiredArgsConstructor
public class MediaController {
    private final MediaService mediaService;

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public Media uploadMedia(
            @RequestParam EntityType entityType,
            @RequestParam Long entityId,
            @RequestParam MultipartFile file,
            @RequestParam(required = false) String description
    ) throws IOException {
        return mediaService.uploadMedia(entityType, entityId, file, description);
    }

    @GetMapping
    public List<Media> getMediaForEntity(
            @RequestParam EntityType entityType,
            @RequestParam Long entityId
    ) {
        return mediaService.getMediaForEntity(entityType, entityId);
    }

    @DeleteMapping("/{id}")
    public void deleteMedia(@PathVariable Long id) {
        mediaService.deleteMedia(id);
    }
} 
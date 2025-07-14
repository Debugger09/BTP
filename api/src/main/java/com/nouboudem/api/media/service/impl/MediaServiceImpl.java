package com.nouboudem.api.media.service.impl;

import com.nouboudem.api.media.entity.Media;
import com.nouboudem.api.media.entity.EntityType;
import com.nouboudem.api.media.repository.MediaRepository;
import com.nouboudem.api.media.service.MediaService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import java.io.File;
import java.io.IOException;
import java.util.List;

@Service
@RequiredArgsConstructor
public class MediaServiceImpl implements MediaService {
    private final MediaRepository mediaRepository;

    @Override
    public Media uploadMedia(EntityType entityType, Long entityId, MultipartFile file, String description) throws IOException {
        String uploadDir = System.getProperty("user.dir") + File.separator + "uploads" + File.separator;
        File dir = new File(uploadDir);
        if (!dir.exists()) dir.mkdirs();
        String path = uploadDir + System.currentTimeMillis() + "_" + file.getOriginalFilename();
        file.transferTo(new File(path));
        String contentType = file.getContentType();
        String typeFichier = (contentType != null && contentType.startsWith("image")) ? "image" : "fichier";
        Media media = Media.builder()
                .entityType(entityType)
                .entityId(entityId)
                .url(path)
                .description(description)
                .typeFichier(typeFichier)
                .build();
        return mediaRepository.save(media);
    }

    @Override
    public List<Media> getMediaForEntity(EntityType entityType, Long entityId) {
        return mediaRepository.findByEntityTypeAndEntityId(entityType, entityId);
    }

    @Override
    public void deleteMedia(Long id) {
        mediaRepository.deleteById(id);
    }
} 
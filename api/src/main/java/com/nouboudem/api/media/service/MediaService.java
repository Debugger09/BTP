package com.nouboudem.api.media.service;

import com.nouboudem.api.media.entity.Media;
import com.nouboudem.api.media.entity.EntityType;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.List;

public interface MediaService {
    Media uploadMedia(EntityType entityType, Long entityId, MultipartFile file, String description) throws IOException;
    List<Media> getMediaForEntity(EntityType entityType, Long entityId);
    void deleteMedia(Long id);
} 
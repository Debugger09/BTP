package com.nouboudem.api.media.repository;

import com.nouboudem.api.media.entity.Media;
import com.nouboudem.api.media.entity.EntityType;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface MediaRepository extends JpaRepository<Media, Long> {
    List<Media> findByEntityTypeAndEntityId(EntityType entityType, Long entityId);
} 
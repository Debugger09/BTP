package com.nouboudem.api.user.mapper;

import com.nouboudem.api.user.dto.UserDto;
import com.nouboudem.api.user.entity.User;
import com.nouboudem.api.media.entity.Media;
import com.nouboudem.api.media.entity.EntityType;
import com.nouboudem.api.media.service.MediaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class UserMapper {
    @Autowired
    private MediaService mediaService;

    public UserDto toDto(User user) {
        if (user == null) return null;
        UserDto dto = new UserDto();
        dto.setId(user.getId());
        dto.setEmail(user.getEmail());
        dto.setNom(user.getNom());
        dto.setPrenom(user.getPrenom());
        dto.setRole(user.getRole());
        dto.setStatut(user.isStatut());
        dto.setPortable(user.getPortable());
        // Ne pas mapper le password en sortie si tu veux le masquer
        if (user.getId() != null) {
            List<Media> medias = mediaService.getMediaForEntity(EntityType.USER, user.getId());
            if (medias != null) {
                for (Media media : medias) {
                    if (media.getDescription() != null && media.getDescription().toLowerCase().contains("photo")) {
                        dto.setPhoto(media);
                    }
                    if (media.getDescription() != null && media.getDescription().toLowerCase().contains("cni")) {
                        dto.setCni(media);
                    }
                }
            }
        }
        return dto;
    }
} 
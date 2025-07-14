package com.nouboudem.api.temoignage.mapper;

import com.nouboudem.api.temoignage.entity.Temoignage;
import com.nouboudem.api.temoignage.dto.TemoignageDto;
import com.nouboudem.api.temoignage.dto.TemoignageCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface TemoignageMapper {
    @Mapping(target = "userId", source = "user.id")
    TemoignageDto toDto(Temoignage entity);
    List<TemoignageDto> toDtoList(List<Temoignage> entities);
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "publier", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "id", ignore = true)
    Temoignage toEntity(TemoignageCreateDto dto);
} 
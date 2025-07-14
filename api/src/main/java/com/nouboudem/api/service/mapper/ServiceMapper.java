package com.nouboudem.api.service.mapper;

import com.nouboudem.api.service.entity.Service;
import com.nouboudem.api.service.dto.ServiceDto;
import com.nouboudem.api.service.dto.ServiceCreateDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.List;

@Mapper(componentModel = "spring")
public interface ServiceMapper {
    ServiceDto toDto(Service entity);
    List<ServiceDto> toDtoList(List<Service> entities);
    @Mapping(target = "id", ignore = true)
    Service toEntity(ServiceCreateDto dto);
} 
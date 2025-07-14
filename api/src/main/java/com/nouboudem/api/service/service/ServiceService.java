package com.nouboudem.api.service.service;

import com.nouboudem.api.service.dto.ServiceDto;
import com.nouboudem.api.service.dto.ServiceCreateDto;
import java.util.List;

public interface ServiceService {
    List<ServiceDto> getAllServices();
    ServiceDto getService(Long id);
    ServiceDto createService(ServiceCreateDto dto);
    ServiceDto updateService(Long id, ServiceCreateDto dto);
    void deleteService(Long id);
} 
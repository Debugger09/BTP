package com.nouboudem.api.service.service.impl;

import com.nouboudem.api.service.dto.ServiceDto;
import com.nouboudem.api.service.dto.ServiceCreateDto;
import com.nouboudem.api.service.entity.Service;
import com.nouboudem.api.service.mapper.ServiceMapper;
import com.nouboudem.api.service.repository.ServiceRepository;
import com.nouboudem.api.service.service.ServiceService;
import lombok.RequiredArgsConstructor;

import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@org.springframework.stereotype.Service
@RequiredArgsConstructor
@Transactional
public class ServiceServiceImpl implements ServiceService {
    private final ServiceRepository serviceRepository;
    private final ServiceMapper serviceMapper;

    @Override
    public List<ServiceDto> getAllServices() {
        return serviceMapper.toDtoList(serviceRepository.findAll());
    }

    @Override
    public ServiceDto getService(Long id) {
        return serviceRepository.findById(id)
                .map(serviceMapper::toDto)
                .orElse(null);
    }

    @Override
    public ServiceDto createService(ServiceCreateDto dto) {
        if (serviceRepository.existsByNom(dto.getNom())) {
            throw new IllegalArgumentException("Un service avec ce nom existe déjà.");
        }
        Service service = serviceMapper.toEntity(dto);
        return serviceMapper.toDto(serviceRepository.save(service));
    }

    @Override
    public ServiceDto updateService(Long id, ServiceCreateDto dto) {
        Optional<Service> opt = serviceRepository.findById(id);
        if (opt.isEmpty()) return null;
        Service service = opt.get();
        service.setNom(dto.getNom());
        service.setDescription(dto.getDescription());
        return serviceMapper.toDto(serviceRepository.save(service));
    }

    @Override
    public void deleteService(Long id) {
        serviceRepository.deleteById(id);
    }
} 
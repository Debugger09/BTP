package com.nouboudem.api.service.controller;

import com.nouboudem.api.service.dto.ServiceDto;
import com.nouboudem.api.service.dto.ServiceCreateDto;
import com.nouboudem.api.service.service.ServiceService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

@RestController
@RequestMapping("/api/services")
@RequiredArgsConstructor
public class ServiceController {
    private final ServiceService serviceService;

    @GetMapping
    public List<ServiceDto> getAllServices() {
        return serviceService.getAllServices();
    }

    @GetMapping("/{id}")
    public ResponseEntity<ServiceDto> getService(@PathVariable Long id) {
        ServiceDto dto = serviceService.getService(id);
        return dto != null ? ResponseEntity.ok(dto) : ResponseEntity.notFound().build();
    }

    @PostMapping
    public ServiceDto createService(@RequestBody ServiceCreateDto dto) {
        return serviceService.createService(dto);
    }

    @PutMapping("/{id}")
    public ResponseEntity<ServiceDto> updateService(@PathVariable Long id, @RequestBody ServiceCreateDto dto) {
        ServiceDto updated = serviceService.updateService(id, dto);
        return updated != null ? ResponseEntity.ok(updated) : ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteService(@PathVariable Long id) {
        serviceService.deleteService(id);
        return ResponseEntity.noContent().build();
    }
} 
package com.nouboudem.api.temoignage.service.impl;

import com.nouboudem.api.temoignage.dto.TemoignageDto;
import com.nouboudem.api.temoignage.dto.TemoignageCreateDto;
import com.nouboudem.api.temoignage.entity.Temoignage;
import com.nouboudem.api.temoignage.mapper.TemoignageMapper;
import com.nouboudem.api.temoignage.repository.TemoignageRepository;
import com.nouboudem.api.temoignage.service.TemoignageService;
import com.nouboudem.api.user.entity.User;
import com.nouboudem.api.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class TemoignageServiceImpl implements TemoignageService {
    private final TemoignageRepository temoignageRepository;
    private final TemoignageMapper temoignageMapper;
    private final UserRepository userRepository;

    @Override
    public List<TemoignageDto> getAllTemoignages() {
        return temoignageMapper.toDtoList(temoignageRepository.findAll());
    }

    @Override
    public List<TemoignageDto> getTemoignagesPublies() {
        return temoignageMapper.toDtoList(temoignageRepository.findByPublierTrue());
    }

    @Override
    public TemoignageDto getTemoignage(Long id) {
        return temoignageRepository.findById(id)
                .map(temoignageMapper::toDto)
                .orElse(null);
    }

    @Override
    public TemoignageDto createTemoignage(TemoignageCreateDto dto) {
        Temoignage temoignage = new Temoignage();
        if (dto.getUserId() != null) {
            Optional<User> user = userRepository.findById(dto.getUserId());
            user.ifPresent(temoignage::setUser);
        }
        temoignage.setContenu(dto.getContenu());
        temoignage.setPublier(false); // Par défaut non publié
        return temoignageMapper.toDto(temoignageRepository.save(temoignage));
    }

    @Override
    public TemoignageDto updateTemoignage(Long id, TemoignageCreateDto dto) {
        Optional<Temoignage> opt = temoignageRepository.findById(id);
        if (opt.isEmpty()) return null;
        Temoignage temoignage = opt.get();
        if (dto.getUserId() != null) {
            Optional<User> user = userRepository.findById(dto.getUserId());
            user.ifPresent(temoignage::setUser);
        } else {
            temoignage.setUser(null);
        }
        temoignage.setContenu(dto.getContenu());
        return temoignageMapper.toDto(temoignageRepository.save(temoignage));
    }

    @Override
    public void deleteTemoignage(Long id) {
        temoignageRepository.deleteById(id);
    }

    @Override
    public TemoignageDto publierTemoignage(Long id, boolean publier) {
        Optional<Temoignage> opt = temoignageRepository.findById(id);
        if (opt.isEmpty()) return null;
        Temoignage temoignage = opt.get();
        temoignage.setPublier(publier);
        return temoignageMapper.toDto(temoignageRepository.save(temoignage));
    }

    @Override
    public List<TemoignageDto> getTemoignagesByUser(Long userId) {
        return temoignageMapper.toDtoList(temoignageRepository.findByUserId(userId));
    }
} 
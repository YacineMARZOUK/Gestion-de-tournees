package com.logistique.gestiontournees.service.impl;

import com.logistique.gestiontournees.dto.TourDTO;
import com.logistique.gestiontournees.entity.Tour;
import com.logistique.gestiontournees.repository.TourRepository;
import com.logistique.gestiontournees.service.TourService;
import com.logistique.gestiontournees.service.mapper.TourMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    public  TourServiceImpl(TourRepository tourRepository, TourMapper tourMapper) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
    }

    @Override
    public TourDTO save(TourDTO tourDTO) {
        Tour tour = tourMapper.toEntity(tourDTO);
        tour = tourRepository.save(tour);
       return tourMapper.toDto(tour);
    }

    @Override
    public Optional<TourDTO> findById(Long id) {
        return tourRepository.findById(id).map(tourMapper::toDto);
    }

    @Override
    public List<TourDTO> findAll() {
        return tourRepository.findAll().stream().map(tourMapper::toDto).collect(Collectors.toList());
    }

    @Override
    public void deleteById(Long id) {
        tourRepository.deleteById(id);
    }
}

package com.logistique.gestiontournees.service;

import com.logistique.gestiontournees.dto.TourDTO;

import java.util.List;
import java.util.Optional;

public interface TourService {
    TourDTO save(TourDTO tourDTO);
    List<TourDTO> findAll();
    Optional<TourDTO> findById(Long id);
    void deleteById(Long id);
}

package com.logistique.gestiontournees.service;

import com.logistique.gestiontournees.entity.Tour;

import java.util.List;
import java.util.Optional;

public interface TourService {
    Tour save(Tour tour);
    List<Tour> findAll();
    Optional<Tour> findById(Long id);
    void deleteById(Long id);
}

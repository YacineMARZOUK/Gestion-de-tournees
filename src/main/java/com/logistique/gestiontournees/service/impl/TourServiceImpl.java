package com.logistique.gestiontournees.service.impl;

import com.logistique.gestiontournees.entity.Tour;
import com.logistique.gestiontournees.repository.TourRepository;
import com.logistique.gestiontournees.service.TourService;

import java.util.List;
import java.util.Optional;

public class TourServiceImpl implements TourService {

    // La dépendance
    private TourRepository tourRepository;

    // Le "setter" pour l'injection XML
    public void setTourRepository(TourRepository tourRepository) {
        this.tourRepository = tourRepository;
    }

    @Override
    public Tour save(Tour tour) {
        // Logique métier avant sauvegarde (ex: vérifier dispo véhicule...)
        return tourRepository.save(tour);
    }

    @Override
    public Optional<Tour> findById(Long id) {
        return tourRepository.findById(id);
    }

    @Override
    public List<Tour> findAll() {
        return tourRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        tourRepository.deleteById(id);
    }
}

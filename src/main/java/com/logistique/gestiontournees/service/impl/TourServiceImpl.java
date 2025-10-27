package com.logistique.gestiontournees.service.impl;

import com.logistique.gestiontournees.dto.TourDTO;
import com.logistique.gestiontournees.entity.Delivery;
import com.logistique.gestiontournees.entity.Tour;
import com.logistique.gestiontournees.entity.Warehouse;
import com.logistique.gestiontournees.repository.TourRepository;
import com.logistique.gestiontournees.repository.WarehouseRepository;
import com.logistique.gestiontournees.service.TourService;
import com.logistique.gestiontournees.service.mapper.TourMapper;
import com.logistique.gestiontournees.service.optimizer.TourOptimizer;
import com.logistique.gestiontournees.util.DistanceCalculator;

import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.stream.Collectors;

public class TourServiceImpl implements TourService {

    private final TourRepository tourRepository;
    private final TourMapper tourMapper;

    private final WarehouseRepository warehouseRepository;
    private final Map<String, TourOptimizer> optimizers;

    public  TourServiceImpl(TourRepository tourRepository, TourMapper tourMapper, WarehouseRepository warehouseRepository, Map<String, TourOptimizer> optimizers) {
        this.tourRepository = tourRepository;
        this.tourMapper = tourMapper;
        this.warehouseRepository = warehouseRepository;
        this.optimizers = optimizers;
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

    @Override
    public TourDTO getOptimizedTour(Long tourId, String algorithmName ){
        TourOptimizer optimizer = optimizers.get(algorithmName);
        if(optimizer == null){
            throw new IllegalArgumentException(" Algorithme non trouvé" + algorithmName);
        }
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tournée non trouvée : " + tourId));
        Warehouse warehouse = warehouseRepository.findById(1L)
                .orElseThrow(()->new RuntimeException("Entrepôt non trouvé. Assurez-vous qu'un entrepôt avec l'ID 1 existe."));

        List<Delivery> ordredDeliveries = optimizer.calculateOptimalTour(
                warehouse,
                tour.getDeliveries(),
                tour.getVehicle()
        );

        tour.setDeliveries(ordredDeliveries);
        Tour updatedTour = tourRepository.save(tour);
        return tourMapper.toDto(updatedTour);
    }

    @Override
    public double getTotalDistance(long tourId) {
        Tour tour = tourRepository.findById(tourId)
                .orElseThrow(() -> new RuntimeException("Tournée non trouvée : " + tourId));

        Warehouse warehouse = warehouseRepository.findById(1L)
                .orElseThrow(() -> new RuntimeException("Entrepôt non trouvé."));

        List<Delivery> orderedDeliveries = tour.getDeliveries();
        if (orderedDeliveries == null || orderedDeliveries.isEmpty()) {
            return 0.0;
        }

        double totalDistance = 0.0;

        double currentLat = warehouse.getLatitude();
        double currentLon = warehouse.getLongitude();

        for (Delivery delivery : orderedDeliveries) {
            totalDistance += DistanceCalculator.calculateDistance(
                    currentLat, currentLon,
                    delivery.getLatitude(), delivery.getLongitude()
            );

            currentLat = delivery.getLatitude();
            currentLon = delivery.getLongitude();
        }

        totalDistance += DistanceCalculator.calculateDistance(
                currentLat, currentLon,
                warehouse.getLatitude(), warehouse.getLongitude()
        );

        return totalDistance;
    }

}



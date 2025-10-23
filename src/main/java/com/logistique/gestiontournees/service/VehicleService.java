package com.logistique.gestiontournees.service;

import com.logistique.gestiontournees.entity.Vehicle;

import java.util.List;
import java.util.Optional;

public interface VehicleService {
    Vehicle save(Vehicle vehicle);
    Optional<Vehicle>findById(Long id);
    List<Vehicle> findAll();
    void deleteById(Long id);

}

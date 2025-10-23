package com.logistique.gestiontournees.service.impl;

import com.logistique.gestiontournees.entity.Vehicle;
import com.logistique.gestiontournees.repository.VehicleRepository;
import com.logistique.gestiontournees.service.VehicleService;

import java.util.List;
import java.util.Optional;

public class VehicleServiceImpl implements VehicleService {
    private VehicleRepository vehicleRepository;

    public void setVehicleRepository(VehicleRepository vehicleRepository){
        this.vehicleRepository=vehicleRepository;
    }

    @Override
    public Vehicle save(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }

    @Override
    public Optional<Vehicle> findById(Long id) {
        return vehicleRepository.findById(id);
    }

    @Override
    public List<Vehicle> findAll() {
        return vehicleRepository.findAll();
    }

    @Override
    public void deleteById(Long id) {
        vehicleRepository.deleteById(id);
    }
}

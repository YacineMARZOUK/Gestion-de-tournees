package com.logistique.gestiontournees.service;

import com.logistique.gestiontournees.dto.VehicleDTO;


import java.util.List;
import java.util.Optional;

public interface VehicleService {
    VehicleDTO save(VehicleDTO vehicle);
    Optional<VehicleDTO>findById(Long id);
    List<VehicleDTO> findAll();
    void deleteById(Long id);

}

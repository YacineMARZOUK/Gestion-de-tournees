package com.logistique.gestiontournees.repository;

import com.logistique.gestiontournees.entity.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

public interface VehicleRepository extends JpaRepository<Vehicle,Long> {
}

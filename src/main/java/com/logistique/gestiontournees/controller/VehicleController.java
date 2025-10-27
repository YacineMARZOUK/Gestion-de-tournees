package com.logistique.gestiontournees.controller;


import com.logistique.gestiontournees.dto.VehicleDTO;
import com.logistique.gestiontournees.entity.Vehicle;
import com.logistique.gestiontournees.service.VehicleService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/vehicles")
public class VehicleController {

    private final VehicleService vehicleService;
    public VehicleController(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Operation(summary = "Crée un nouveau véhicule")
    @PostMapping
    public ResponseEntity<VehicleDTO> createVehicle(@RequestBody VehicleDTO vehicleDTO) {

        if(vehicleDTO.getId()==null){
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        VehicleDTO savedDto = vehicleService.save(vehicleDTO);
        return new ResponseEntity<VehicleDTO>(savedDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Récupère un véhicule par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<VehicleDTO> getVehicleById(@PathVariable Long id) {
        Optional<VehicleDTO> dtoOpt = vehicleService.findById(id);

        // Utilise ResponseEntity pour renvoyer 200 (OK) si trouvé, ou 404 (Not Found) sinon
        return dtoOpt
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }
}

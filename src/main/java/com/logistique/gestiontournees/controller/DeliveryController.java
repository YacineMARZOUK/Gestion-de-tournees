package com.logistique.gestiontournees.controller;


import com.logistique.gestiontournees.dto.DeliveryDTO;
import com.logistique.gestiontournees.service.DeliveryService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api/deliveries")
public class DeliveryController {

    private final DeliveryService deliveryService;

    public DeliveryController(DeliveryService deliveryService) {
        this.deliveryService = deliveryService;
    }

    @Operation(summary = "Crée une nouvelle livraison")
    @PostMapping
    public ResponseEntity<DeliveryDTO> createDelivery(@RequestBody DeliveryDTO deliveryDTO) {
        if (deliveryDTO.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        DeliveryDTO savedDto = deliveryService.save(deliveryDTO);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }

    @Operation(summary = "Récupère une livraison par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<DeliveryDTO> getDeliveryById(@PathVariable Long id) {
        Optional<DeliveryDTO> dtoOpt = deliveryService.findById(id);

        return dtoOpt.map(dto ->ResponseEntity.ok(dto)).orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


}

package com.logistique.gestiontournees.controller;

import com.logistique.gestiontournees.dto.TourDTO;
import com.logistique.gestiontournees.service.TourService;
import io.swagger.v3.oas.annotations.Operation;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/api/tours") // URL de base pour les tournées
public class TourController {

    private final TourService tourService;

    public TourController(TourService tourService) {
        this.tourService = tourService;
    }


    @Operation(summary = "Crée une nouvelle tournée")
    @PostMapping
    public ResponseEntity<TourDTO> createTour(@RequestBody TourDTO tourDTO) {
        if (tourDTO.getId() != null) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }

        TourDTO savedDto = tourService.save(tourDTO);
        return new ResponseEntity<>(savedDto, HttpStatus.CREATED);
    }


    @Operation(summary = "Récupère une tournée par son ID")
    @GetMapping("/{id}")
    public ResponseEntity<TourDTO> getTourById(@PathVariable Long id) {
        Optional<TourDTO> dtoOpt = tourService.findById(id);

        return dtoOpt
                .map(dto -> ResponseEntity.ok(dto))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }


    @Operation(summary = "Récupère la liste de toutes les tournées")
    @GetMapping
    public ResponseEntity<List<TourDTO>> getAllTours() {
        List<TourDTO> tours = tourService.findAll();
        return ResponseEntity.ok(tours);
    }

    @Operation(summary = "Met à jour une tournée existante")
    @PutMapping("/{id}")
    public ResponseEntity<TourDTO> updateTour(@PathVariable Long id, @RequestBody TourDTO tourDTO) {
        if (tourService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tourDTO.setId(id); // Force l'ID
        TourDTO updatedDto = tourService.save(tourDTO);
        return ResponseEntity.ok(updatedDto);
    }

    @Operation(summary = "Supprime une tournée par son ID")
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteTour(@PathVariable Long id) {
        if (tourService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        tourService.deleteById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @Operation(summary = "Lance l'optimisation d'une tournée avec un algorithme donné")
    @PostMapping("/{id}/optimize") //
    public ResponseEntity<TourDTO> optimizeTour(
            @PathVariable("id") Long id,
            @RequestParam("algoName") String algorithmName) {

        if (tourService.findById(id).isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        try {

            TourDTO optimizedTour = tourService.getOptimizedTour(id, algorithmName);
            return ResponseEntity.ok(optimizedTour);

        } catch (IllegalArgumentException e) {

            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        } catch (RuntimeException e) {

            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
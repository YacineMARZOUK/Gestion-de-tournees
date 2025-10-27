package com.logistique.gestiontournees.runner;

import com.logistique.gestiontournees.dto.VehicleDTO;
import com.logistique.gestiontournees.entity.enumeration.VehicleType;
import com.logistique.gestiontournees.service.VehicleService;
import org.springframework.boot.CommandLineRunner;

import java.util.List;

/**
 * Ce bean est exécuté par Spring Boot au démarrage.
 * Nous l'utilisons pour un test rapide de la base de données.
 * IL N'A PAS D'ANNOTATION (ex: @Component)
 */
public class DatabaseTestRunner implements CommandLineRunner {

    // Injecté par constructeur via le XML
    private final VehicleService vehicleService;

    public DatabaseTestRunner(VehicleService vehicleService) {
        this.vehicleService = vehicleService;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n\n--- [DÉBUT TEST BDD] ---");

        try {
            // 1. Créer un DTO de test
            VehicleDTO testBike = VehicleDTO.builder()
                    .licensePlate("TEST-BIKE-123")
                    .vehicleType(VehicleType.BIKE)
                    .maxWeight(50)
                    .maxVolume(0.5)
                    .build();

            System.out.println("[TEST] Tentative de sauvegarde de : " + testBike);

            // 2. Appeler le service pour sauvegarder
            VehicleDTO savedBike = vehicleService.save(testBike);

            System.out.println("[TEST] Véhicule sauvegardé avec succès ! ID attribué : " + savedBike.getId());

            // 3. Appeler le service pour lire
            List<VehicleDTO> allVehicles = vehicleService.findAll();
            System.out.println("[TEST] Nombre total de véhicules en BDD : " + allVehicles.size());
            System.out.println(allVehicles);

            System.out.println("--- [TEST BDD RÉUSSI] --- \n\n");

        } catch (Exception e) {
            System.err.println("--- [ERREUR TEST BDD] ---");
            e.printStackTrace();
            System.err.println("--- [ERREUR TEST BDD] --- \n\n");
        }
    }
}
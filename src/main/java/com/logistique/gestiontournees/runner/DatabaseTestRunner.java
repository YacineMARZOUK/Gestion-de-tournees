package com.logistique.gestiontournees.runner;

import com.logistique.gestiontournees.entity.Warehouse; // Importez l'ENTITÉ
import com.logistique.gestiontournees.repository.WarehouseRepository; // Importez le REPOSITORY
import org.springframework.boot.CommandLineRunner;
import java.time.LocalTime;

/**
 * Ce bean crée l'entrepôt de base (ID=1) au démarrage.
 */
public class DatabaseTestRunner implements CommandLineRunner {

    // 1. Injecter le REPOSITORY directement
    private final WarehouseRepository warehouseRepository;

    // 2. Le constructeur n'a besoin que du repository
    public DatabaseTestRunner(WarehouseRepository warehouseRepository) {
        this.warehouseRepository = warehouseRepository;
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("\n\n--- [CRÉATION DE L'ENTREPÔT ID=1] ---");

        // 3. On utilise le Builder de l'ENTITÉ
        Warehouse warehouse = Warehouse.builder()
                .address("Entrepôt Central, Lyon")
                .latitude(45.7500) // Coordonnées de Lyon
                .longitude(4.8500)
                .openingTime(LocalTime.of(6, 0))
                .closingTime(LocalTime.of(22, 0))
                .build();

        // 4. On sauvegarde l'ENTITÉ directement avec le repository
        Warehouse savedWarehouse = warehouseRepository.save(warehouse);

        System.out.println("[DATA] Entrepôt créé avec ID: " + savedWarehouse.getId());
        System.out.println("--- [PRÊT POUR LES TESTS POSTMAN] ---\n\n");
    }
}
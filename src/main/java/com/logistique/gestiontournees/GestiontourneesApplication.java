package com.logistique.gestiontournees;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;

@ImportResource("classpath:applicationContext.xml")
@SpringBootApplication
public class GestiontourneesApplication {

    public static void main(String[] args) {
        SpringApplication.run(GestiontourneesApplication.class, args);
    }

}

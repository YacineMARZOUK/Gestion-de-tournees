package com.logistique.gestiontournees.service;

import com.logistique.gestiontournees.entity.Delivery;

import java.util.List;
import java.util.Optional;

public interface DeliveryService {
    Delivery save(Delivery delivery);
    Optional<Delivery> findById(Long id);
    List<Delivery> findAll();
    void deleteById(Long id);
}

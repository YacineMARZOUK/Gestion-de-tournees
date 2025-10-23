package com.logistique.gestiontournees.service.impl;

import com.logistique.gestiontournees.entity.Delivery;
import com.logistique.gestiontournees.repository.DeliveryRepository;
import com.logistique.gestiontournees.service.DeliveryService;

import java.util.List;
import java.util.Optional;

public class DeliveryServiceImpl implements DeliveryService {
    private DeliveryRepository deliveryRepository;

    public void setDeliveryRepository(DeliveryRepository deliveryRepository) {
        this.deliveryRepository = deliveryRepository;
    }

    @Override
    public Delivery save(Delivery delivery) {
        return deliveryRepository.save(delivery);
    }

    @Override
    public List<Delivery> findAll(){
        return deliveryRepository.findAll();
    }

    @Override
    public Optional<Delivery>findById(Long id){
        return deliveryRepository.findById(id);
    }

    @Override
    public void deleteById(Long id){
        deliveryRepository.deleteById(id);
    }

}

package com.logistique.gestiontournees.service.impl;

import com.logistique.gestiontournees.dto.DeliveryDTO;
import com.logistique.gestiontournees.entity.Delivery;
import com.logistique.gestiontournees.repository.DeliveryRepository;
import com.logistique.gestiontournees.service.DeliveryService;
import com.logistique.gestiontournees.service.mapper.DeliveryMapper;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

public class DeliveryServiceImpl implements DeliveryService {
    private final  DeliveryRepository deliveryRepository;
    private final DeliveryMapper deliveryMapper;

    public  DeliveryServiceImpl(DeliveryRepository deliveryRepository, DeliveryMapper deliveryMapper) {
        this.deliveryRepository = deliveryRepository;
        this.deliveryMapper = deliveryMapper;
    }

    @Override
    public DeliveryDTO save(DeliveryDTO deliveryDTO) {
         Delivery delivery = deliveryMapper.toEntity(deliveryDTO);
         delivery = deliveryRepository.save(delivery);
         return deliveryMapper.toDto(delivery);
    }

    @Override
    public List<DeliveryDTO> findAll(){
        return deliveryRepository.findAll().stream()
                .map(deliveryMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public Optional<DeliveryDTO>findById(Long id){
        return deliveryRepository.findById(id).map(deliveryMapper::toDto);
    }

    @Override
    public void deleteById(Long id){
        deliveryRepository.deleteById(id);
    }

}

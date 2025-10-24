package com.logistique.gestiontournees.service.mapper.impl;

import com.logistique.gestiontournees.dto.DeliveryDTO;
import com.logistique.gestiontournees.entity.Delivery;
import com.logistique.gestiontournees.entity.Tour;
import com.logistique.gestiontournees.service.mapper.DeliveryMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class DeliveryMapperImpl implements DeliveryMapper, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }


    @Override
    public Delivery toEntity(DeliveryDTO dto){
        Delivery entity = (Delivery)  applicationContext.getBean("deliveryEntity");
        if(dto == null){
            return entity;
        }
        entity.setId(dto.getId());
        entity.setAddress(dto.getAddress());
        entity.setLatitude(dto.getLatitude());
        entity.setLongitude(dto.getLongitude());
        entity.setWeight(dto.getWeight());
        entity.setVolume(dto.getVolume());
        entity.setStatus(dto.getStatus());
        entity.setTimeSlot(dto.getTimeSlot());

        // Gérer la relation
        if (dto.getTourId() != null) {
            Tour tour = (Tour) applicationContext.getBean("tourEntity");
            tour.setId(dto.getTourId());
            entity.setTour(tour);
        }
        return entity;
    }

    @Override
    public DeliveryDTO toDto(Delivery entity) {
        if (entity == null) {
            return null;
        }
        DeliveryDTO dto = (DeliveryDTO) applicationContext.getBean("deliveryDTO");
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setLatitude(entity.getLatitude());
        dto.setLongitude(entity.getLongitude());
        dto.setWeight(entity.getWeight());
        dto.setVolume(entity.getVolume());
        dto.setStatus(entity.getStatus());
        dto.setTimeSlot(entity.getTimeSlot());

        // Gérer la relation
        if (entity.getTour() != null) {
            dto.setTourId(entity.getTour().getId());
        }
        return dto;
    }
    }

}

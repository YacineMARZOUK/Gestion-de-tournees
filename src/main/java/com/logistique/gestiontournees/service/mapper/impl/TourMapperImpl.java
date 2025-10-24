package com.logistique.gestiontournees.service.mapper.impl;

import com.logistique.gestiontournees.service.mapper.DeliveryMapper;
import com.logistique.gestiontournees.service.mapper.TourMapper;
import com.logistique.gestiontournees.service.mapper.VehicleMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class TourMapperImpl implements TourMapper, ApplicationContextAware {

    private ApplicationContext applicationContext;
    private final VehicleMapper vehicleMapper;
    private final DeliveryMapper deliveryMapper;


    public TourMapperImpl(VehicleMapper vehicleMapper, DeliveryMapper deliveryMapper) {
        this.vehicleMapper = vehicleMapper;
        this.deliveryMapper = deliveryMapper;
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }



}

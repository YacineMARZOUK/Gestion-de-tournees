package com.logistique.gestiontournees.service.mapper.impl;

import com.logistique.gestiontournees.dto.VehicleDTO;
import com.logistique.gestiontournees.entity.Vehicle;
import com.logistique.gestiontournees.service.mapper.VehicleMapper;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class VehicleMapperImpl implements VehicleMapper, ApplicationContextAware {

    private ApplicationContext applicationContext;

    @Override
    public void setApplicationContexte(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }
    @Override
    public Vehicle toEntity (VehicleDTO dto){

        if(dto == null){
            return null;
        }
        Vehicle entity = (Vehicle) applicationContext.getBean("vehicle");
        entity.setId(dto.getId());
        entity.setLicensePlate( dto.getLicensePlate() );
        entity.setVehicleType(dto.getType());
        entity.setMaxWeight( dto.getMaxWeight() );
        entity.setMaxVolume( dto.getMaxVolume() );
        return entity;
    }

    @Override
    public VehicleDTO toDto (Vehicle entity){
        VehicleDTO dto = (VehicleDTO) applicationContext.getBean("vehicleDto");
        dto.setId(entity.getId());
        dto.setLicensePlate(entity.getLicensePlate());
        dto.setType(entity.getVehicleType());
        dto.setMaxWeight(entity.getMaxWeight());
        dto.setMaxVolume(entity.getMaxVolume());
        return dto;
    }

}

package com.logistique.gestiontournees.service.mapper.impl;

import com.logistique.gestiontournees.dto.VehicleDTO;
import com.logistique.gestiontournees.entity.Vehicle;
import com.logistique.gestiontournees.service.mapper.VehicleMapper;

public class VehicleMapperImpl implements VehicleMapper {

    @Override
    public Vehicle toEntity (VehicleDTO dto){

        if(dto == null){
            return null;
        }
        Vehicle entity = new Vehicle();
        entity.setId(dto.getId());
        entity.setLicensePlate( dto.getLicensePlate() );
        entity.setVehicleType(dto.getType());
        entity.setMaxWeight( dto.getMaxWeight() );
        entity.setMaxVolume( dto.getMaxVolume() );
        return entity;
    }

    @Override
    public VehicleDTO toDto (Vehicle entity){
        VehicleDTO dto = new VehicleDTO();
        dto.setId(entity.getId());
        dto.setLicensePlate(entity.getLicensePlate());
        dto.setType(entity.getVehicleType());
        dto.setMaxWeight(entity.getMaxWeight());
        dto.setMaxVolume(entity.getMaxVolume());
        return dto;
    }

}

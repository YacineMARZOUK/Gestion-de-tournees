package com.logistique.gestiontournees.service.optimizer.impl;

import com.logistique.gestiontournees.entity.Delivery;
import com.logistique.gestiontournees.entity.Vehicle;
import com.logistique.gestiontournees.entity.Warehouse;
import com.logistique.gestiontournees.service.optimizer.TourOptimizer;
import com.logistique.gestiontournees.util.DistanceCalculator;

import java.util.ArrayList;
import java.util.List;

public class NearestNeighborOptimizer implements TourOptimizer {

    @Override
    public List<Delivery> calculateOptimalTour(Warehouse warehouse, List<Delivery> deliveries, Vehicle vehicle){

        //la copie de loriginal
        List<Delivery> remainingDeliveries = new ArrayList<>(deliveries);
        List<Delivery> orderedTour = new ArrayList<>();

        //le point de depart
        double currentlat = warehouse.getLatitude();
        double currentLon = warehouse.getLongitude();

        while(!remainingDeliveries.isEmpty()){
            Delivery closesDelivery = null;
            double shortestDistance = Double.MAX_VALUE;

            for(Delivery delivery : remainingDeliveries){
                double distance = DistanceCalculator.calculateDistance(currentlat, currentLon,
                        delivery.getLatitude(), delivery.getLongitude());

                if(distance < shortestDistance){
                    shortestDistance = distance;
                    closesDelivery = delivery;
                }
                if (closesDelivery != null){
                    orderedTour.add(closesDelivery);
                    remainingDeliveries.remove(closesDelivery);

                    currentlat = closesDelivery.getLatitude();
                    currentLon = closesDelivery.getLongitude();
                }else{
                    break;
                }
            }
            return orderedTour;
        }


    }


}

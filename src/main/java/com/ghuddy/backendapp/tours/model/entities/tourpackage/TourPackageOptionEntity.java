package com.ghuddy.backendapp.tours.model.entities.tourpackage;

import com.ghuddy.backendapp.tours.model.entities.transfer.TransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.FoodOptionEntity;
import lombok.Data;

@Data
public class TourPackageOptionEntity {
    private AccommodationOptionEntity accommodationOptionEntity;
    private FoodOptionEntity foodOptionEntity;
    private TransferOptionEntity transferOptionEntity;
    private TransportationPackageEntity transportationPackageEntity;

}

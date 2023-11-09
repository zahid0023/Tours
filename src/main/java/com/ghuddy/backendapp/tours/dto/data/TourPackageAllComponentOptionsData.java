package com.ghuddy.backendapp.tours.dto.data;

import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageAllComponentOptionsData {
    private List<AccommodationOptionData> accommodationOptionDataList;
    private List<FoodOptionData> foodOptionDataList;
    private List<TransferOptionData> transferOptionDataList;
    private List<TransportationPackageData> transportationPackageDataList;

    public TourPackageAllComponentOptionsData(TourPackageEntity tourPackageEntity) {
        this.accommodationOptionDataList = tourPackageEntity
    }
}

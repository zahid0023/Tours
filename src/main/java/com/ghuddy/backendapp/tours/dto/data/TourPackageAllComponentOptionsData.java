package com.ghuddy.backendapp.tours.dto.data;

import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.guide.GuideOptionData;
import com.ghuddy.backendapp.tours.model.data.spot.entry.SpotEntryData;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageAllComponentOptionsData {
    private List<AccommodationOptionData> accommodationOptionDataList;
    private List<FoodOptionData> foodOptionDataList;
    private List<TransferOptionData> transferOptionDataList;
    private List<TransportationPackageData> transportationPackageDataList;
    private List<GuideOptionData> guideOptionDataList;
    private List<SpotEntryData> spotEntryDataList;


    public TourPackageAllComponentOptionsData(TourPackageEntity tourPackageEntity) {
        /*this.accommodationOptionDataList = tourPackageEntity.getAccommodationOptionEntities().stream()
                .map(accommodationOptionEntity -> new AccommodationOptionData(accommodationOptionEntity))
                .toList();
        this.foodOptionDataList = tourPackageEntity.getFoodOptionEntities().stream()
                .map(foodOptionEntity -> new FoodOptionData(foodOptionEntity))
                .toList();
        this.transferOptionDataList = tourPackageEntity.getTransferOptionEntities().stream()
                .map(transferOptionEntity -> new TransferOptionData(transferOptionEntity))
                .toList();
        this.transportationPackageDataList = tourPackageEntity.getTransportationPackageEntities().stream()
                .map(transportationPackageEntity -> new TransportationPackageData(transportationPackageEntity))
                .toList();
        this.guideOptionDataList = tourPackageEntity.getGuideOptionEntityList().stream()
                .map(guideOptionEntity -> new GuideOptionData(guideOptionEntity))
                .toList();
        this.spotEntryDataList = tourPackageEntity.getSpotEntryEntities().stream()
                .map(spotEntryEntity -> new SpotEntryData(spotEntryEntity))
                .toList();
         */
    }
}

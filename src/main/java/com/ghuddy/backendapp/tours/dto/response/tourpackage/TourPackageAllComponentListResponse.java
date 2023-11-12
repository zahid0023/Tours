package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.accommodation.AvailabilityGeneratedAccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.AvailabilityGeneratedFoodOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.AvailabilityGeneratedTransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.AvailabilityGeneratedTransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageAllComponentListResponse extends BaseResponse {
    private Long tourPackageTypeId;
    private List<AvailabilityGeneratedAccommodationOptionData> availabilityGeneratedAccommodationOptionDataList;
    private List<AvailabilityGeneratedFoodOptionData> availabilityGeneratedFoodOptionDataList;
    private List<AvailabilityGeneratedTransferOptionData> availabilityGeneratedTransferOptionDataList;
    private List<AvailabilityGeneratedTransportationPackageData> availabilityGeneratedTransportationPackageDataList;

    public TourPackageAllComponentListResponse(TourPackageEntity tourPackageEntity, String requestId){
        this.tourPackageTypeId = tourPackageEntity.getTourPackageType().getId();
        this.availabilityGeneratedAccommodationOptionDataList = tourPackageEntity.getAccommodationOptionEntities().stream()
                .map(accommodationOptionEntity -> new AvailabilityGeneratedAccommodationOptionData(accommodationOptionEntity))
                .toList();
        this.availabilityGeneratedFoodOptionDataList = tourPackageEntity.getFoodOptionEntities().stream()
                .map(foodOptionEntity -> new AvailabilityGeneratedFoodOptionData(foodOptionEntity))
                .toList();
        this.availabilityGeneratedTransferOptionDataList = tourPackageEntity.getTransferOptionEntities().stream()
                .map(transferOptionEntity -> new AvailabilityGeneratedTransferOptionData(transferOptionEntity))
                .toList();
        this.availabilityGeneratedTransportationPackageDataList = tourPackageEntity.getTransportationPackageEntities().stream()
                .map(transportationPackageEntity -> new AvailabilityGeneratedTransportationPackageData(transportationPackageEntity))
                .toList();
        this.setRequestId(requestId);
    }
}

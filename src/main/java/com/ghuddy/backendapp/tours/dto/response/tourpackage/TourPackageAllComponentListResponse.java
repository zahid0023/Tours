package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.accommodation.AvailabilityGeneratedAccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.AvailabilityGeneratedFoodOptionData;
import com.ghuddy.backendapp.tours.model.data.guide.AvailabilityGeneratedGuideOptionData;
import com.ghuddy.backendapp.tours.model.data.spot.entry.AvailabilityGeneratedSpotEntryOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.AvailabilityGeneratedTransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.AvailabilityGeneratedTransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageAllComponentListResponse extends BaseResponse {
    @Schema(description = "The tour package type id")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeId;
    @Schema(description = "The accommodation options")
    @JsonProperty("tour_package_accommodation_options")
    private List<AvailabilityGeneratedAccommodationOptionData> availabilityGeneratedAccommodationOptionDataList;
    @Schema(description = "The food options")
    @JsonProperty("tour_package_food_options")
    private List<AvailabilityGeneratedFoodOptionData> availabilityGeneratedFoodOptionDataList;
    @Schema(description = "The transfer options")
    @JsonProperty("tour_package_transfer_options")
    private List<AvailabilityGeneratedTransferOptionData> availabilityGeneratedTransferOptionDataList;
    @Schema(description = "The transportation packages")
    @JsonProperty("tour_package_transportation_packages")
    private List<AvailabilityGeneratedTransportationPackageData> availabilityGeneratedTransportationPackageDataList;
    @Schema(description = "The spot entry options")
    @JsonProperty("tour_package_spot_entry_options")
    private List<AvailabilityGeneratedSpotEntryOptionData> availabilityGeneratedSpotEntryOptionDataList;

    @Schema(description = "The guide options")
    @JsonProperty("tour_package_guide_options")
    private List<AvailabilityGeneratedGuideOptionData> availabilityGeneratedGuideOptionDataList;

    public TourPackageAllComponentListResponse(TourPackageEntity tourPackageEntity, String requestId){
        this.tourPackageTypeId = tourPackageEntity.getTourPackageType().getId();
        this.availabilityGeneratedAccommodationOptionDataList = tourPackageEntity.getAccommodationOptionEntities().stream()
                .map(accommodationOptionEntity -> new AvailabilityGeneratedAccommodationOptionData(accommodationOptionEntity))
                .toList();
        //this.availabilityGeneratedFoodOptionDataList = tourPackageEntity.getFoodOptionEntities().stream()
                //.map(foodOptionEntity -> new AvailabilityGeneratedFoodOptionData(foodOptionEntity))
                //.toList();
        this.availabilityGeneratedTransferOptionDataList = tourPackageEntity.getTransferOptionEntities().stream()
                .map(transferOptionEntity -> new AvailabilityGeneratedTransferOptionData(transferOptionEntity))
                .toList();
        this.availabilityGeneratedTransportationPackageDataList = tourPackageEntity.getTransportationPackageEntities().stream()
                .map(transportationPackageEntity -> new AvailabilityGeneratedTransportationPackageData(transportationPackageEntity))
                .toList();
        this.availabilityGeneratedSpotEntryOptionDataList = tourPackageEntity.getTourPackageSpotEntryOptionEntities().stream()
                .map(tourPackageSpotEntryOptionEntity -> new AvailabilityGeneratedSpotEntryOptionData(tourPackageSpotEntryOptionEntity))
                .toList();
        this.availabilityGeneratedGuideOptionDataList = tourPackageEntity.getGuideOptionEntityList().stream()
                .map(guideOptionEntity -> new AvailabilityGeneratedGuideOptionData(guideOptionEntity))
                .toList();
        this.setRequestId(requestId);
    }
}

package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.guide.GuideOptionData;
import com.ghuddy.backendapp.tours.model.data.spot.entry.SpotEntryOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageData {
    @Schema(description = "The tour package type id")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeId;
    @Schema(description = "The id of the tour package")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The name of the tour package type")
    @JsonProperty("tour_package_type_name")
    private String tourPackageTypeName;
    @Schema(description = "The name of the tour package")
    @JsonProperty("tour_package_name")
    private String tourPackageName;
    @Schema(description = "The description of the tour package")
    @JsonProperty("tour_package_description")
    private String tourPackageDescription;

    @Schema(description = "The list of accommodation options belonging to this tour package")
    @JsonProperty("tour_package_accommodation_options")
    private List<AccommodationOptionData> accommodationOptionDataList;

    @Schema(description = "The list of food options belonging to this tour package")
    @JsonProperty("tour_package_food_options")
    private List<FoodOptionData> foodOptionDataList;

    @Schema(description = "The list of transfer options belonging to this tour package")
    @JsonProperty("tour_package_transfer_options")
    private List<TransferOptionData> transferOptionDataList;

    @Schema(description = "The list of transportation packages belonging to this tour package")
    @JsonProperty("tour_package_transportation_packages")
    private List<TransportationPackageData> transportationPackageDataList;

    @Schema(description = "The list of guide options belonging to this tour package")
    @JsonProperty("tour_package_guide_options")
    private List<GuideOptionData> guideOptionDataList;
    @Schema(description = "The list of spot entries belonging to this tour package")
    @JsonProperty("tour_package_sport_entries")
    private List<SpotEntryOptionData> spotEntryOptionDataLast;

    public TourPackageData(TourPackageEntity tourPackageEntity) {
        this.tourPackageTypeId = tourPackageEntity.getTourPackageType().getId();
        this.tourPackageId = tourPackageEntity.getId();
        this.tourPackageTypeName = tourPackageEntity.getTourPackageType().getPackageTypeName();
        this.tourPackageName = tourPackageEntity.getTourPackageName();
        this.tourPackageDescription = tourPackageEntity.getDescription();
        this.accommodationOptionDataList = tourPackageEntity.getAccommodationOptionEntities().stream()
                .map(accommodationOptionEntity -> new AccommodationOptionData(accommodationOptionEntity, true))
                .toList();
        this.foodOptionDataList = tourPackageEntity.getFoodOptionEntities().stream()
                .map(foodOptionEntity -> new FoodOptionData(foodOptionEntity, true))
                .toList();
        this.transferOptionDataList = tourPackageEntity.getTransferOptionEntities().stream()
                .map(transferOptionEntity -> new TransferOptionData(transferOptionEntity, true))
                .toList();
        this.transportationPackageDataList = tourPackageEntity.getTransportationPackageEntities().stream()
                .map(transportationPackageEntity -> new TransportationPackageData(transportationPackageEntity, true))
                .toList();
        this.guideOptionDataList = tourPackageEntity.getGuideOptionEntityList().stream()
                .map(guideOptionEntity -> new GuideOptionData(guideOptionEntity, true))
                .toList();
        this.spotEntryOptionDataLast = tourPackageEntity.getTourPackageSpotEntryOptionEntities().stream()
                .map(tourPackageSpotEntryOptionEntity -> new SpotEntryOptionData(tourPackageSpotEntryOptionEntity))
                .toList();

    }
}

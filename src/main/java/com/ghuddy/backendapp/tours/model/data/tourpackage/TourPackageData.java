package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.data.DefaultCombinationData;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TourPackageData {
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
    @Schema(description = "The list of the food options belonging to this tour package")
    @JsonProperty("tour_package_food_options")
    private List<FoodOptionData> foodOptionDataList;
    @Schema(description = "The list of the accommodation options belonging to this tour package")
    @JsonProperty("tour_package_accommodation_options")
    private List<AccommodationOptionData> accommodationOptionDataList;
    @Schema(description = "The list of the transfer option belonging to this tour package")
    @JsonProperty("tour_package_transfer_options")
    private List<TransferOptionData> transferOptionDataList;

    @Schema(description = "The list of the transportation packages belonging to this tour package")
    @JsonProperty("tour_package_transportation_packages")
    private List<TransportationPackageData> transportationPackageDataList;

    @Schema(description = "The default food option price per person")
    @JsonProperty("tour_package_default_option")
    private DefaultCombinationData defaultCombinationData;

    @Schema(description = "The total price of the tour package")
    @JsonProperty("tour_package_total_price")
    private BigDecimal totalPackagePrice;


    public TourPackageData(TourPackageEntity tourPackageEntity) {
        this.tourPackageId = tourPackageEntity.getId();
        this.tourPackageTypeName = tourPackageEntity.getTourPackageType().getPackageTypeName();
        this.tourPackageName = tourPackageEntity.getTourPackageName();
        this.tourPackageDescription = tourPackageEntity.getDescription();
        this.defaultCombinationData = new DefaultCombinationData();
        defaultCombinationData.setDefaultOptionPricePerPerson(BigDecimal.ZERO);
        this.totalPackagePrice = tourPackageEntity.getTotalPackagePrice();
        this.foodOptionDataList = tourPackageEntity.getFoodOptionEntities().stream()
                .map(foodOptionEntity -> {
                    FoodOptionData foodOptionData = new FoodOptionData(foodOptionEntity);
                    if (foodOptionEntity.getIsDefault()) {
                        defaultCombinationData.setFoodOptionData(foodOptionData);
                        defaultCombinationData.getDefaultOptionPricePerPerson().add(foodOptionEntity.getTotalOptionPricePerPerson());
                    }
                    return foodOptionData;
                }).toList();
        this.accommodationOptionDataList = tourPackageEntity.getAccommodationOptionEntities().stream()
                .map(accommodationOptionEntity -> {
                    AccommodationOptionData accommodationOptionData = new AccommodationOptionData(accommodationOptionEntity);
                    if (accommodationOptionEntity.getIsDefault()) {
                        defaultCombinationData.setAccommodationOptionData(accommodationOptionData);
                        defaultCombinationData.getDefaultOptionPricePerPerson().add(accommodationOptionEntity.getTotalOptionPricePerPerson());
                    }
                    return accommodationOptionData;
                }).toList();
        this.transferOptionDataList = tourPackageEntity.getTourTransferOptionEntities().stream()
                .map(transferOptionEntity -> {
                    TransferOptionData transferOptionData = new TransferOptionData(transferOptionEntity);
                    if (transferOptionEntity.getIsDefault()) {
                        defaultCombinationData.setTransferOptionData(transferOptionData);
                        defaultCombinationData.getDefaultOptionPricePerPerson().add(transferOptionEntity.getPerPersonTransferOptionPrice());
                    }
                    return transferOptionData;
                }).toList();
        this.transportationPackageDataList = tourPackageEntity.getTransportationPackageEntities().stream()
                .map(transportationPackageEntity -> new TransportationPackageData(transportationPackageEntity)).toList();
    }
}

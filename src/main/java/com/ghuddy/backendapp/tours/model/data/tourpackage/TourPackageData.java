package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.data.ComponentCombinationData;
import com.ghuddy.backendapp.tours.dto.data.DefaultCombinationData;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationOptionData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.transfer.TransferOptionData;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.ArrayList;
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

    @Schema(description = "The list of component combination/ options belonging to this tour package")
    @JsonProperty("tour_package_options")
    private List<ComponentCombinationData> componentCombinationDataList;

    @Schema(description = "The list of transportation packages belonging to this tour package")
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

        this.componentCombinationDataList = tourPackageEntity.getTourPackageOptionEntities().stream()
                .map(tourPackageOptionEntity -> {
                    ComponentCombinationData componentCombinationData = new ComponentCombinationData();
                    componentCombinationData.setTotalOptionPricePerPerson(BigDecimal.ZERO);

                    FoodOptionData foodOptionData = new FoodOptionData(tourPackageOptionEntity.getFoodOptionEntity());
                    if (foodOptionData != null) {
                        componentCombinationData.setFoodOptionData(foodOptionData);
                        componentCombinationData.setTotalOptionPricePerPerson(componentCombinationData.getTotalOptionPricePerPerson().add(foodOptionData.getTotalOptionPricePerPerson()));
                        if (foodOptionData.isDefault()) {
                            defaultCombinationData.setFoodOptionData(foodOptionData);
                            defaultCombinationData.setDefaultOptionPricePerPerson(defaultCombinationData.getDefaultOptionPricePerPerson().add(foodOptionData.getTotalOptionPricePerPerson()));
                        }
                    }

                    AccommodationOptionData accommodationOptionData = new AccommodationOptionData(tourPackageOptionEntity.getAccommodationOptionEntity());

                    if (accommodationOptionData != null) {
                        componentCombinationData.setAccommodationOptionData(accommodationOptionData);
                        componentCombinationData.setTotalOptionPricePerPerson(componentCombinationData.getTotalOptionPricePerPerson().add(accommodationOptionData.getTotalOptionPricePerPerson()));
                        if (accommodationOptionData.isDefault()) {
                            defaultCombinationData.setAccommodationOptionData(accommodationOptionData);
                            defaultCombinationData.setDefaultOptionPricePerPerson(defaultCombinationData.getDefaultOptionPricePerPerson().add(accommodationOptionData.getTotalOptionPricePerPerson()));
                        }
                    }

                    TransferOptionData transferOptionData = new TransferOptionData(tourPackageOptionEntity.getTransferOptionEntity());
                    if (transferOptionData != null && transferOptionData.isDefault()) {
                        componentCombinationData.setTransferOptionData(transferOptionData);
                        componentCombinationData.setTotalOptionPricePerPerson(componentCombinationData.getTotalOptionPricePerPerson().add(transferOptionData.getTotalOptionPricePerPerson()));
                        if (transferOptionData.isDefault()) {
                            defaultCombinationData.setTransferOptionData(transferOptionData);
                            defaultCombinationData.setDefaultOptionPricePerPerson(defaultCombinationData.getDefaultOptionPricePerPerson().add(transferOptionData.getTotalOptionPricePerPerson()));
                        }
                    }
                    return componentCombinationData;
                })
                .toList();

        this.transportationPackageDataList = tourPackageEntity.getTransportationPackageEntities().stream()
                .map(transportationPackageEntity -> new TransportationPackageData(transportationPackageEntity))
                .toList();

    }
}

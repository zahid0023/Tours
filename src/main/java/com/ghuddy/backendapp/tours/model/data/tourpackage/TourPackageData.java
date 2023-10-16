package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.accommodation.AccommodationPackageData;
import com.ghuddy.backendapp.tours.model.data.food.FoodOptionData;
import com.ghuddy.backendapp.tours.model.data.food.MealPackageData;
import com.ghuddy.backendapp.tours.model.data.transportation.TourPackageTransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

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
    @Schema(description = "The list of the meal packages belonging to this tour package")
    @JsonProperty("tour_package_meal_packages")
    private List<FoodOptionData> mealPackageDataList;
    @Schema(description = "The list of the accommodation packages belonging to this tour package")
    @JsonProperty("tour_package_accommodation_packages")
    private List<AccommodationPackageData> accommodationPackageDataList;

    @Schema(description = "The list of the transportation packages belonging to this tour package")
    @JsonProperty("tour_package_transportation_packages")
    private List<TourPackageTransportationPackageData> tourPackageTransportationPackageDataList;

    @Schema(description = "The net price of this tour package")
    @JsonProperty("tour_package_net_price")
    private BigDecimal netPrice;
    @Schema(description = "The added/subtracted price of this tour package")
    @JsonProperty("tour_package_added_price")
    private BigDecimal addedPrice;
    @Schema(description = "The total/final price of this meal package")
    @JsonProperty("tour_package_total_price")
    private BigDecimal totalPackagePrice;


    public TourPackageData(TourPackageEntity tourPackageEntity) {
        this.tourPackageId = tourPackageEntity.getId();
        this.tourPackageTypeName = tourPackageEntity.getTourPackageType().getPackageTypeName();
        this.tourPackageName = tourPackageEntity.getTourPackageName();
        this.tourPackageDescription = tourPackageEntity.getDescription();
        /* this.mealPackageDataList = tourPackageEntity.getMealPackageEntities().stream()
                .map(mealPackageEntity -> new MealPackageData(mealPackageEntity))
                .collect(Collectors.toList());*/
        /*this.accommodationPackageDataList = tourPackageEntity.getAccommodationPackageEntities().stream()
                .map(tourPackageAccommodationEntity -> new AccommodationPackageData(tourPackageAccommodationEntity))
                .collect(Collectors.toList());*/
        this.tourPackageTransportationPackageDataList = tourPackageEntity.getTransportationPackageEntities().stream()
                .map(tourPackageTransportationEntity -> new TourPackageTransportationPackageData(tourPackageTransportationEntity))
                .collect(Collectors.toList());
        this.totalPackagePrice = tourPackageEntity.getTotalPackagePrice();
    }
}

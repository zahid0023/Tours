package com.ghuddy.backendapp.tours.model.data.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageAccommodationPackageData;
import com.ghuddy.backendapp.tours.model.data.food.MealPackageData;
import com.ghuddy.backendapp.tours.model.data.transportation.TourPackageTransportationPackageData;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TourPackageData {
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
    private List<MealPackageData> mealPackageDataList;
    @Schema(description = "The list of the accommodation packages belonging to this tour package")
    @JsonProperty("tour_package_accommodation_packages")
    private List<TourPackageAccommodationPackageData> tourPackageAccommodationPackageDataList;

    @Schema(description = "The list of the transportation packages belonging to this tour package")
    @JsonProperty("tour_package_transportation_packages")
    private List<TourPackageTransportationPackageData> tourPackageTransportationPackageDataList;


    public TourPackageData(TourPackageEntity tourPackageEntity) {
        this.tourPackageTypeName = tourPackageEntity.getTourPackageType().getPackageTypeName();
        this.tourPackageName = tourPackageEntity.getTourPackageName();
        this.tourPackageDescription = tourPackageEntity.getDescription();
        this.mealPackageDataList = tourPackageEntity.getMealPackageEntities().stream()
                .map(mealPackageEntity -> new MealPackageData(mealPackageEntity))
                .collect(Collectors.toList());
        this.tourPackageAccommodationPackageDataList = tourPackageEntity.getTourPackageAccommodationEntities().stream()
                .map(tourPackageAccommodationEntity -> new TourPackageAccommodationPackageData(tourPackageAccommodationEntity))
                .collect(Collectors.toList());
        this.tourPackageTransportationPackageDataList = tourPackageEntity.getTourPackageTransportationEntities().stream()
                .map(tourPackageTransportationEntity -> new TourPackageTransportationPackageData(tourPackageTransportationEntity))
                .collect(Collectors.toList());
    }
}

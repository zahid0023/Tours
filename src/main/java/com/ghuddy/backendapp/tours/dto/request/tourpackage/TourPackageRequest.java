package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.accommodation.TourPackageAccommodationRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TourPackageTransportationRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageRequest {
    @Schema(description = "The ID of the tourPackageType.", required = true, example = "1")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeID;
    @Schema(description = "The description of the tour package.", required = true, example = "tour package description")
    @JsonProperty("tour_package_description")
    private String tourPackageDescription;
    @Schema(description = "The list of the meal packages belonging to this tour package.")
    @JsonProperty("tour_package_meal_packages")
    private List<MealPackageRequest> tourPackageMealPackages;

    @Schema(description = "The list of the accommodation packages belonging to this tour package.")
    @JsonProperty("tour_package_accommodation_packages")
    private List<TourPackageAccommodationRequest> tourPackageAccommodations;

    @Schema(description = "The list of transportation packages belonging to this tour")
    @JsonProperty("tour_package_transportation_packages")
    private List<TourPackageTransportationRequest> tourPackageTransportations;
}

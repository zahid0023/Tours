package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.accommodation.TourPackageAccommodationRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageRequest extends BaseRequest {
    @Schema(description = "The ID of the tourPackageType.", required = true, example = "1")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeID;
    @Schema(description = "The description of the tour package.", required = true, example = "tour package description")
    @JsonProperty("description")
    private String description;
    @Schema(description = "The list of the meal packages belonging to this tour package.")
    @JsonProperty("meal_packages")
    private List<MealPackageRequest> mealPackages;
    @Schema(description = "The list of the accommodations belonging to this tour package.")
    @JsonProperty("accommodations")
    private List<TourPackageAccommodationRequest> accommodations;

    @Override
    public void validate() throws AbstractException {

    }
}

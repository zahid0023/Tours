package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.ghuddy.backendapp.tours.dto.request.accommodation.TourPackageAccommodationRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageRequest {
    @Schema(description = "The ID of the tourPackageType.", required = true, example = "1")
    private Long tourPackageTypeID;
    @Schema(description = "The description of the tour package.", required = true, example = "tour package description")
    private String description;
    @Schema(description = "The list of the meal packages belonging to this tour package.")
    private List<MealPackageRequest> mealPackages;
    @Schema(description = "The list of the accommodations belonging to this tour package.")
    private List<TourPackageAccommodationRequest> accommodations;
}

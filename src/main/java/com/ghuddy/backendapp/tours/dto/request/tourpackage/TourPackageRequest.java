package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.guide.GuideOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.guide.GuidePackageRequest;
import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class TourPackageRequest {
    @Schema(description = "The ID of the tourPackageType.", required = true, example = "1")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeID;
    @Schema(description = "The description of the tour package.", required = true, example = "tour package description")
    @JsonProperty("tour_package_description")
    private String tourPackageDescription;
    @Schema(description = "The list of the accommodation options belonging to this tour package.")
    @JsonProperty("tour_package_accommodation_options")
    private List<AccommodationOptionRequest> accommodationOptionRequestList;
    @Schema(description = "The list of the food options belonging to this tour package.")
    @JsonProperty("tour_package_meal_packages")
    private List<MealPackageRequest> mealPackageRequestList;
    @Schema(description = "The list of transfer options belonging to this tour package")
    @JsonProperty("tour_package_transfer_options")
    private List<TransferOptionRequest> transferOptionRequestList;
    @Schema(description = "The list of transportation packages belonging to this tour package.")
    @JsonProperty("tour_package_transportation_packages")
    private List<TransportationPackageRequest> transportationPackages;
    @Schema(description = "The list of guide option belonging to this tour package.")
    @JsonProperty("tour_package_guide_options")
    private List<GuideOptionRequest> guideOptionRequestList;
    @Schema(description = "the list of spot entry for which fee has to be paid")
    @JsonProperty("spot_entries")
    private List<SpotEntryRequest> spotEntryRequestList;
}

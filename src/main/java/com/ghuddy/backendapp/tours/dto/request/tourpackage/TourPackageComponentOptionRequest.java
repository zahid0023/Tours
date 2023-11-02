package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageComponentOptionRequest {
    @Schema(description = "The food option belonging to this tour package option.")
    @JsonProperty("tour_package_food_option")
    private FoodOptionRequest foodOptionRequest;
    @Schema(description = "The accommodation option belonging to this tour package option.")
    @JsonProperty("tour_package_accommodation_option")
    private AccommodationOptionRequest accommodationOptionRequest;
    @Schema(description = "The transfer option belonging to this tour package option")
    @JsonProperty("tour_package_transfer_option")
    private TransferOptionRequest transferOptionRequest;
}

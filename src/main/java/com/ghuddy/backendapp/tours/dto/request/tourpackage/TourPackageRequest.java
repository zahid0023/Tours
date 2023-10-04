package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TourPackageTransportationRequest;
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
    @Schema(description = "The list of the meal packages belonging to this tour package.")
    @JsonProperty("tour_package_meal_packages")
    private List<MealPackageRequest> mealPackages;
    @Schema(description = "The list of the accommodation packages belonging to this tour package.")
    @JsonProperty("tour_package_accommodation_packages")
    private List<AccommodationPackageRequest> accommodationPackages;
    @Schema(description = "The list of transportation packages belonging to this tour")
    @JsonProperty("tour_package_transportation_packages")
    private List<TourPackageTransportationRequest> transportationPackages;
    @Schema(description = "The price after adding all the default component prices", required = true, example = "200")
    @JsonProperty("package_net_price")
    private BigDecimal netPrice;
    @Schema(description = "The price merchant may wish to add/subtract from the final package price, basically merchant adjust the price", required = true, example = "100")
    @JsonProperty("package_added_price")
    private BigDecimal addedPrice;
    @Schema(description = "The final price of the package which is the sum of the net price and the added price", required = true, example = "300")
    @JsonProperty("package_total_price")
    private BigDecimal totalPackagePrice;
}

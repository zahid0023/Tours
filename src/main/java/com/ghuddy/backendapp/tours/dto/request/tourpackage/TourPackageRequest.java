package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.guide.GuidePackageRequest;
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
    @Schema(description = "The list of the food options belonging to this tour package.")
    @JsonProperty("tour_package_food_options")
    private List<FoodOptionRequest> foodOptionRequestList;
    @Schema(description = "The list of the accommodation options belonging to this tour package.")
    @JsonProperty("tour_package_accommodation_options")
    private List<AccommodationOptionRequest> accommodationOptionRequestList;
    @Schema(description = "The list of transfer options belonging to this tour package")
    @JsonProperty("tour_package_transfer_options")
    private List<TransferOptionRequest> transferOptionRequestList;
    @Schema(description = "The list of transportation packages belonging to this tour package.")
    @JsonProperty("tour_package_transportation_packages")
    private List<TransportationPackageRequest> transportationPackages;

    @Schema(description = "The guide belonging to this tour package")
    @JsonProperty("tour_package_guide")
    private GuidePackageRequest guidePackageRequest;

    @Schema(description = "The final price of the package which is the sum of all the default component package price", required = true, example = "300")
    @JsonProperty("package_total_price")
    private BigDecimal totalPackagePrice;
}

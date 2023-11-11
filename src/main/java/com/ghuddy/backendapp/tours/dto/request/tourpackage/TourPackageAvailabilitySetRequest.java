package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.guide.GuideOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryPackageRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequestForAvailability;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class TourPackageAvailabilitySetRequest extends BaseRequest {
    @Schema(description = "The tour package id for which merchant want to generate availability", required = true, example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The start date of the tour", required = true, example = "2023-11-14")
    @JsonProperty("tour_start_date")
    private LocalDate tourStartDate;
    @Schema(description = "The total seats available for this tour", required = true, example = "5")
    @JsonProperty("total_seats")
    private Integer totalSeats;
    @Schema(description = "Total bookable seats in tour", required = true, example = "5")
    @JsonProperty("bookable_seats")
    private Integer bookableSeats;
    @Schema(description = "The list of accommodation options offered in this tour package")
    @JsonProperty("accommodation_options")
    private List<AccommodationOptionRequestForAvailability> accommodationOptionRequestForAvailabilityList;
    @Schema(description = "The list of food options offered in this tour package")
    @JsonProperty("food_options")
    private List<FoodOptionRequestForAvailability> foodOptionRequestForAvailabilityList;
    @Schema(description = "The list of transfer options offered in this tour package")
    @JsonProperty("transfer_options")
    private List<TransferOptionRequestForAvailability> transferOptionRequestForAvailabilityList;
    @Schema(description = "The list of transportation packages offered in this tour package")
    @JsonProperty("transportation_packages")
    private List<TransportationPackageRequestForAvailability> transportationPackageRequestForAvailabilityList;
    @Schema(description = "The list of guide options offered in this tour package")
    @JsonProperty("guide_options")
    private List<GuideOptionRequestForAvailability> guideOptionRequestForAvailabilityList;
    @Schema(description = "The list of spot entries options offered in this tour package")
    @JsonProperty("spot_entries")
    private List<SpotEntryPackageRequestForAvailability> spotEntryPackageRequestForAvailabilityList;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}

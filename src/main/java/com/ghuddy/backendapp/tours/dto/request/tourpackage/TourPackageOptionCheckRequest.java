package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.guide.GuidePackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class TourPackageOptionCheckRequest extends BaseRequest {
    @Schema(description = "The id of the tour package type for which the options are checked")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeId;
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

    @Schema(description = "The number of traveller for this tour package")
    @JsonProperty("number_of_travellers")
    private Integer numberOfTravellers;

    public TourPackageOptionCheckRequest(TourPackageRequest tourPackageRequest, Integer numberOfTravellers) {
        this.tourPackageTypeId = tourPackageRequest.getTourPackageTypeID();
        this.foodOptionRequestList = tourPackageRequest.getFoodOptionRequestList();
        this.accommodationOptionRequestList = tourPackageRequest.getAccommodationOptionRequestList();
        this.transferOptionRequestList = tourPackageRequest.getTransferOptionRequestList();
        this.transportationPackages = tourPackageRequest.getTransportationPackages();
        this.guidePackageRequest = tourPackageRequest.getGuidePackageRequest();
        this.numberOfTravellers = numberOfTravellers;
    }

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}

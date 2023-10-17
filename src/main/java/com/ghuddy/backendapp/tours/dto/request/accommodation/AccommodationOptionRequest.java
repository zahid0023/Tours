package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AccommodationOptionRequest {
    @Schema(description = "The accommodation that will be associated with the tour package given in the id", required = true)
    @JsonProperty("tour_package_accommodations_package_list")
    private List<TourPackageAccommodationRequest> tourPackageAccommodationRequestList;
    @Schema(description = "Whether this is the default accommodation option for the tour package", required = true, example = "false")
    @JsonProperty("tour_package_default_accommodation_option")
    private Boolean isDefault;

}

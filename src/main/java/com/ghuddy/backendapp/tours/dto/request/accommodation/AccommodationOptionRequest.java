package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AccommodationOptionRequest extends OptionRequest {
    @Schema(description = "The accommodation that will be associated with the tour package given in the id", required = true)
    @JsonProperty("tour_package_accommodations_package_list")
    private List<AccommodationPackageRequest> tourPackageAccommodationRequestList;

}

package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AccommodationOptionRequestForAvailability extends OptionRequest {
    @Schema(description = "The accommodation packages belonging to this available tour package", required = true)
    @JsonProperty("accommodation_packages")
    private List<AccommodationPackageRequestForAvailability> accommodationPackageRequestForAvailabilityList;
}

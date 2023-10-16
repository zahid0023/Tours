package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class AccommodationOptionDTO {
    @Schema(description = "The accommodation packages along with their key")
    @JsonProperty("accommodation_package_list")
    private Map<String, AccommodationPackageRequest> accommodationPackageMapping;

    @Schema(description = "The mapping between night and in which room type the traveler will stay for this option", required = true)
    @JsonProperty("accommodation_options")
    private List<OptionRequest> accommodationOptions;


}

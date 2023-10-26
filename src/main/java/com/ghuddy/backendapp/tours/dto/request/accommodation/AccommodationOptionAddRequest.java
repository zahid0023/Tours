package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AccommodationOptionAddRequest extends BaseRequest {
    @Schema(description = "The id of the tour package to which this accommodation will be associated", required = true, example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageID;

    @Schema(description = "A single combination of accommodation packages", required = true)
    @JsonProperty("tour_package_accommodation_option")
    private AccommodationOptionRequest accommodationOptionRequest;

    @Override
    public void validate() throws AbstractException {

    }
}

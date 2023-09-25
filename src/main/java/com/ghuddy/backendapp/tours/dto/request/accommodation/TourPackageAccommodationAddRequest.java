package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageAccommodationAddRequest extends BaseRequest {
    @Schema(description = "The id of the tour package to which this accommodation will be associated", required = true, example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageID;
    @Schema(description = "The accommodation that will be associated with the tour package given in the id", required = true)
    @JsonProperty("tour_package_accommodation")
    private TourPackageAccommodationRequest tourPackageAccommodation;

    @Override
    public void validate() throws AbstractException {

    }
}

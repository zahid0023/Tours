package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AccommodationOptionListAddRequest extends BaseRequest {
    @Schema(description = "The id of the tour package", required = true, example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageID;

    @Schema(description = "All combinations of accommodation packages for this tour package by the merchant", required = true)
    @JsonProperty("tour_package_accommodation_options")
    private List<AccommodationOptionRequest> accommodationOptionRequestList;

    @Override
    public void validate() throws AbstractException {

    }
}

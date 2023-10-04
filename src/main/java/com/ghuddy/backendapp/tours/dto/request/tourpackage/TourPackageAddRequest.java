package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageAddRequest extends BaseRequest {
    @Schema(description = "The id of the tour", required = true, example = "1")
    @JsonProperty("subscribed_tour_id")
    private Long subscribedTourID;
    @Schema(description = "The tour package that will be stored in the database", required = true)
    @JsonProperty("tour_package")
    private TourPackageRequest tourPackageRequest;

    @Override
    public void validate() throws AbstractException {

    }
}

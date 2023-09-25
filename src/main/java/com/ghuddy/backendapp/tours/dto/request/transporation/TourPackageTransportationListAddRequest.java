package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageTransportationListAddRequest extends BaseRequest {
    @Schema(description = "The tour package id", required = true, example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageID;
    @Schema(description = "The list of transportations that will be associated with the tour package")
    @JsonProperty("tour_package_transportations")
    private List<TourPackageTransportationRequest> tourPackageTransportations;

    @Override
    public void validate() throws AbstractException {

    }
}

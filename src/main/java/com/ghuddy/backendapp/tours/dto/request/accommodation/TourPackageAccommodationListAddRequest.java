package com.ghuddy.backendapp.tours.dto.request.accommodation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageAccommodationListAddRequest {
    @Schema(description = "The id of the tour package", required = true, example = "1")
    private Long tourPackageID;

    @Schema(description = "The list of accommodation provided for this tour package by the merchant", required = true)
    List<TourPackageAccommodationRequest> tourPackageAccommodations;
}

package com.ghuddy.backendapp.tours.dto.response.tour;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.tour.TourDetailsPageData;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageDetailsPageData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourDetailsPageResponse {
    @Schema(description = "The tour details")
    @JsonProperty("tour_details")
    TourDetailsPageData tourDetailsPageData;

    public TourDetailsPageResponse(TourDetailsPageData tourDetailsPageData) {
        this.tourDetailsPageData = tourDetailsPageData;
    }
}

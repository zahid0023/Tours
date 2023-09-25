package com.ghuddy.backendapp.tours.dto.response.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.data.TourAccommodationData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourAccommodationListResponse {
    @Schema(description = "The list of the tour accommodation")
    @JsonProperty("tour_accommodations")
    private List<TourAccommodationData> tourAccommodationDataList;

    public TourAccommodationListResponse(List<TourAccommodationData> tourAccommodationDataList) {
        this.tourAccommodationDataList = tourAccommodationDataList;
    }
}

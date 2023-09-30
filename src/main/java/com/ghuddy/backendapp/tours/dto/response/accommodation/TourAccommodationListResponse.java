package com.ghuddy.backendapp.tours.dto.response.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageAccommodationData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourAccommodationListResponse extends BaseResponse {
    @Schema(description = "The list of the tour accommodation")
    @JsonProperty("tour_accommodations")
    private List<TourPackageAccommodationData> tourPackageAccommodationDataList;

    public TourAccommodationListResponse(List<TourPackageAccommodationData> tourPackageAccommodationDataList, String requestId) {
        this.tourPackageAccommodationDataList = tourPackageAccommodationDataList;
        this.setRequestId(requestId);
    }
}

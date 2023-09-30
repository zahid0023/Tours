package com.ghuddy.backendapp.tours.dto.response.accommodation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.accommodation.TourPackageAccommodationTypeData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourAccommodationTypeListResponse extends BaseResponse {
    @Schema(description = "The list of accommodation types")
    @JsonProperty("tour_accommodation_types")
    private List<TourPackageAccommodationTypeData> tourPackageAccommodationTypeDataList;

    public TourAccommodationTypeListResponse(List<TourPackageAccommodationTypeData> tourPackageAccommodationTypeDataList, String requestId) {
        this.tourPackageAccommodationTypeDataList = tourPackageAccommodationTypeDataList;
        this.setRequestId(requestId);
    }
}

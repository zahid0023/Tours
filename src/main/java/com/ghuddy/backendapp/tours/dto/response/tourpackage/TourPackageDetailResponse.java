package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageDetailResponse extends BaseResponse {
    @Schema(description = "The tour package details")
    @JsonProperty("tour_package")
    private TourPackageData tourPackageData;

    public TourPackageDetailResponse(TourPackageData tourPackageData, String requestId) {
        this.tourPackageData = tourPackageData;
        this.setRequestId(requestId);
    }
}

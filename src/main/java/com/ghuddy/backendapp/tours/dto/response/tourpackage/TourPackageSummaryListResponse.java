package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageSummaryData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageSummaryListResponse extends BaseResponse {
    @Schema(description = "The tour package summary")
    @JsonProperty("tour_packages")
    private List<TourPackageSummaryData> tourPackageSummaryDataList;

    public TourPackageSummaryListResponse(List<TourPackageSummaryData> tourPackageSummaryDataList, String requestId) {
        this.tourPackageSummaryDataList = tourPackageSummaryDataList;
        this.setRequestId(requestId);
    }
}

package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.dto.data.TourPackageCoreComponentData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TourPackageOptionListResponse extends BaseResponse {
    @Schema(description = "The list of combination of core components")
    @JsonProperty("tour_package_core_options")
    private List<TourPackageCoreComponentData> tourPackageCoreComponentDataList;

    public TourPackageOptionListResponse(List<TourPackageCoreComponentData> tourPackageCoreComponentDataList, String requestId) {
        super(requestId);
        this.tourPackageCoreComponentDataList = tourPackageCoreComponentDataList;
    }
}

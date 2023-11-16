package com.ghuddy.backendapp.tours.dto.request.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class GuideOptionRequest {
    @Schema(description = "The tour package guide packages")
    @JsonProperty("tour_package_guide_packages")
    private List<GuidePackageRequest> guidePackageRequestList;
}

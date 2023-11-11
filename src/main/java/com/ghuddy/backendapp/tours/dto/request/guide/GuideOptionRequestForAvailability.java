package com.ghuddy.backendapp.tours.dto.request.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
public class GuideOptionRequestForAvailability extends OptionRequest {
    @Schema(description = "The guide package packages",required = true)
    @JsonProperty("tour_package_guide_packages")
    private List<GuidePackageRequestForAvailability> guidePackageRequestForAvailabilityList;
}

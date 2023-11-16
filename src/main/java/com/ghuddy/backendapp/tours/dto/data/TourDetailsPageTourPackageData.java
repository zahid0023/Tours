package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;

public class TourDetailsPageTourPackageData {
    @Schema(description = "The tour package type id")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeId;
    @Schema(description = "The id of the tour package")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The name of the tour package type")
    @JsonProperty("tour_package_type_name")
    private String tourPackageTypeName;
    @Schema(description = "The name of the tour package")
    @JsonProperty("tour_package_name")
    private String tourPackageName;
    @Schema(description = "The description of the tour package")
    @JsonProperty("tour_package_description")
    private String tourPackageDescription;


}

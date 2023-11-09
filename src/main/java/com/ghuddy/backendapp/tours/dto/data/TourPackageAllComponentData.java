package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.transportation.TransportationPackageData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageAllComponentData extends TourPackageCoreComponentData {
    @Schema(description = "The transportation package")
    @JsonProperty("tour_package_transportation_package")
    private TransportationPackageData transportationPackageData;
}

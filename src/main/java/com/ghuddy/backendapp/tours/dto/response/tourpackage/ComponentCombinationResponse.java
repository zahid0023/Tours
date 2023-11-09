package com.ghuddy.backendapp.tours.dto.response.tourpackage;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.data.TourPackageAllComponentData;
import com.ghuddy.backendapp.tours.dto.data.DefaultCombinationData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ComponentCombinationResponse {
    @Schema(description = "The combination of all the options")
    @JsonProperty("tour_package_component_combinations")
    private List<TourPackageAllComponentData> tourPackageAllComponentCombinationData;
    @Schema(description = "The default combination")
    @JsonProperty("tour_package_default_component_combination")
    private DefaultCombinationData defaultCombinationData;

    public ComponentCombinationResponse(List<TourPackageAllComponentData> tourPackageAllComponentCombinationData, DefaultCombinationData defaultCombinationData){
        this.tourPackageAllComponentCombinationData = tourPackageAllComponentCombinationData;
        this.defaultCombinationData = defaultCombinationData;
    }
}

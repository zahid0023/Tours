package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.dto.request.OptionRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransportationOptionRequest { // it will be transformed to only containing field List<Transportation Packages>
    @Schema(description = "The id of the transportation route", required = true, example = "1")
    @JsonProperty("transportation_route_id")
    private Long transportationRouteID;
    @Schema(description = "The id of the transportation mode", required = true, example = "1")
    @JsonProperty("transportation_mode_id")
    private Long transportationModeID;
    @Schema(description = "The id of the transportation brand", required = true, example = "1")
    @JsonProperty("transportation_brand_id")
    private Long transportationBrandID;
    @Schema(description = "Where the transportation has ac or not", example = "true")
    @JsonProperty("is_ac")
    private Boolean isAC;
    @Schema(description = "The id of the transportation provider", required = true, example = "[1,2,3]")
    @JsonProperty("transportation_provider_ids")
    private List<Long> transportationProviderIDs;

}

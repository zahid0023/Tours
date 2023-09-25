package com.ghuddy.backendapp.tours.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationRouteData {
    @Schema(description = "The id of the route", example = "1")
    @JsonProperty("transportation_route_id")
    private Long transportationRouteId;
    @Schema(description = "The location from where the traveler will be given transportation facility", example = "Dhaka")
    @JsonProperty("transportation_route_source_location")
    private String transportationRouteSourceLocation;
    @Schema(description = "The location to where the traveler will be given transportation facility", example = "Bandarban")
    @JsonProperty("transportation_route_destination_location")
    private String transportationRouteDestinationLocation;
}

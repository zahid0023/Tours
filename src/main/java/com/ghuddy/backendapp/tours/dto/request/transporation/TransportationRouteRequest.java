package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationRouteRequest extends BaseRequest {
    @Schema(description = "The id of the location from where the transportation service will be provided", required = true, example = "1")
    @JsonProperty("source_location_id")
    private Long sourceLocationID;
    @Schema(description = "The id of the location to where the transportation service will be provided", required = true, example = "1")
    @JsonProperty("destination_location_id")
    private Long destinationLocationID;

    @Override
    public void validate() throws AbstractException {

    }
}

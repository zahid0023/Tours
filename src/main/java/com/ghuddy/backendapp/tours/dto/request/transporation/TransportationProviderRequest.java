package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationProviderRequest extends BaseRequest {
    @Schema(description = "The name of the transportation provider", required = true, example = "Hanif")
    @JsonProperty("transportation_provider_name")
    private String transportationProviderName;
    @Schema(description = "The help line number of the transportation provider")
    @JsonProperty("hot_line_number")
    private String hotlineNumber;

    @Override
    public void validate() throws AbstractException {

    }
}

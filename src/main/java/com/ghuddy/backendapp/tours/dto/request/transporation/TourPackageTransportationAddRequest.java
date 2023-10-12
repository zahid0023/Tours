package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageTransportationAddRequest extends BaseRequest {
    @Schema(description = "The id of the tour package", required = true, example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageID;
    @Schema(description = "The transportation associated with this tour package", required = true)
    @JsonProperty("tour_package_transportation")
    private TransportationPackageRequest tourPackageTransportation;

    @Override
    public void validate() throws AbstractException {

    }
}

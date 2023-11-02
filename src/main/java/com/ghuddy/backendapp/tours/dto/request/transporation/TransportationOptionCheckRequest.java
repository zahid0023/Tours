package com.ghuddy.backendapp.tours.dto.request.transporation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransportationOptionCheckRequest extends BaseRequest {
    @Schema(description = "The tour package type for which the combination will be generated", required = true, example = "1")
    @JsonProperty("tour_package_type_id")
    private Long tourPackageTypeId;

    @Schema(description = "The list of basically packages to be checked and returned as options to the front end", required = true)
    @JsonProperty("tour_package_transportation_packages")
    private List<TransportationOptionRequest> transportationOptionRequestList;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}

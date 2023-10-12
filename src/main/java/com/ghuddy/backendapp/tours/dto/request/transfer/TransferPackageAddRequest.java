package com.ghuddy.backendapp.tours.dto.request.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransferPackageAddRequest {
    @Schema(description = "The id of the the tour package to which the transfer packages belong to", required = true, example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The transfer packages of the tour package", required = true)
    @JsonProperty("tour_package_transfer_packages")
    private List<TransferPackageRequest> transferPackageRequests;
}

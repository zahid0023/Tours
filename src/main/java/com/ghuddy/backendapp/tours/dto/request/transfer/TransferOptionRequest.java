package com.ghuddy.backendapp.tours.dto.request.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransferOptionRequest {
    @Schema(description = "The combination of all the transfer packages belonging to this option")
    @JsonProperty("tour_package_transfer_packages")
    private List<TransferPackageRequest> transferPackageRequestList;
    @Schema(description = "Whether this is the default option for the tour package", required = true, example = "false")
    @JsonProperty("tour_package_default_transfer_option")
    private Boolean isDefault;
}

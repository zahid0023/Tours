package com.ghuddy.backendapp.tours.dto.request.tourpackage;

import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageRequest;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransferPackageListAddRequest {
    @Schema(description = "The id of the tour package",required = true,example = "1")
    private Long tourPackageId;
    @Schema(description = "The list of of transfer packages belonging to this tour package",required = true)
    private List<TransferPackageRequest> transferPackageRequests;
}

package com.ghuddy.backendapp.tours.dto.request.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransferOptionAddRequest extends BaseRequest {
    @Schema(description = "The id of the the tour package to which the transfer packages belong to", required = true, example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The transfer option belonging to the tour package", required = true)
    @JsonProperty("tour_package_transfer_option")
    private TransferOptionRequest transferOptionRequest;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}

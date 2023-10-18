package com.ghuddy.backendapp.tours.dto.request.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class TransferOptionListAddRequest extends BaseRequest {
    @Schema(description = "The id of the tour package",required = true,example = "1")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The list of transfer packages belonging to this tour package",required = true)
    @JsonProperty("tour_package_transfer_options")
    private List<TransferOptionRequest> transferOptionRequestList;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}

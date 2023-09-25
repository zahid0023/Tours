package com.ghuddy.backendapp.tours.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AcknowledgeResponse extends BaseResponse {

    @Schema(required = true,
            description = "The action was successful",
            example = "Successful")
    @JsonProperty("status")
    private final String status;
    @Schema(required = true,
            description = "the status code of the action performed",
            example = "00000")
    @JsonProperty("status_code")
    private final String statusCode;

    public AcknowledgeResponse() {
        this.status = "Successful";
        this.statusCode = "00000";
    }

    public AcknowledgeResponse(String status, String statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }
}

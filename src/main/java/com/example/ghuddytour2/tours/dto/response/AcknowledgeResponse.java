package com.example.ghuddytour2.tours.dto.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class AcknowledgeResponse {

    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "The action was successful",
            example = "Successful")
    private final String status;
    @Schema(requiredMode = Schema.RequiredMode.REQUIRED,
            description = "the status code of the action performed",
            example = "00000")
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

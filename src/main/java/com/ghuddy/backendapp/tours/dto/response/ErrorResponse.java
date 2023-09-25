package com.ghuddy.backendapp.tours.dto.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.dto.response.BaseResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class ErrorResponse extends BaseResponse {
    @Schema(description = "The status message of the error", required = true)
    @JsonProperty("status")
    private String status;
    @Schema(description = "The code associated with this error", required = true)
    @JsonProperty("status_code")
    private String statusCode;

    public ErrorResponse(ErrorCode statusCode) {
        this.status = statusCode.toString();
        this.statusCode = statusCode.getCode();
    }

    public ErrorResponse(String status, String statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }

    public ErrorResponse(String message, int statusCode) {
        this.status = message;
        this.statusCode = String.valueOf(statusCode);
    }

}


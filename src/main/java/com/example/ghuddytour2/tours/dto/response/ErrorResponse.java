package com.example.ghuddytour2.tours.dto.response;

import com.example.ghuddytour2.enums.ErrorCode;
import lombok.Data;

@Data
public class ErrorResponse {
    private String status;
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


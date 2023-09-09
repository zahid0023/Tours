package com.example.ghuddytour2.tours.dto.response;

import lombok.Data;

@Data
public class TourAddResponse {
    private final String status;
    private final String statusCode;

    public TourAddResponse(String status, String statusCode) {
        this.status = status;
        this.statusCode = statusCode;
    }
}

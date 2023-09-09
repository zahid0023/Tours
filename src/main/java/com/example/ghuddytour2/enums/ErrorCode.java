package com.example.ghuddytour2.enums;

/*
 * Error Codes:
 * Location Error : starts with 0, example 00001
 * Tour Error: starts with 1, example 10001
 * */
public enum ErrorCode {

    // Location Errors
    // Starts with 0
    LOCATION_NOT_FOUND("00001"),

    // Tour Errors
    // Starts with 1
    TOUR_NOT_FOUND("10001"),
    TOUR_ALREADY_EXIST("10002");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}


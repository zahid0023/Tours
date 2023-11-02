package com.ghuddy.backendapp.tours.enums;

/*
 * Error Codes:
 * Location Error : starts with 0, example 00001
 * Tour Error: starts with 1, example 10001
 * */
public enum ErrorCode {

    // Location Exceptions
    // Starts with 0
    LOCATION_NOT_FOUND("00001"),

    // Tour Exceptions
    // Starts with 1
    TOUR_NOT_FOUND("10001"),
    TOUR_ALREADY_EXIST("10002"),

    // Activity Exceptions
    // Starts with 2
    ACTIVITY_TYPE_ALREADY_EXISTS("20001"),
    ACTIVITY_TYPE_NOT_FOUND("20002"),
    ACTIVITY_ALREADY_EXISTS("20003"),
    ACTIVITY_NOT_FOUND("2004"),
    ACTIVITY_CAN_NOT_BE_SAVED("2005"),

    // User Exceptions
    MERCHANT_NOT_FOUND("30001"),

    // Tour Package Exceptions
    TOUR_PACKAGE_NOT_FOUND("40001"),


    // Generic Exceptions
    // Starts with 11
    LIST_IS_EMPTY("11001"),
    DATA_SAVE_ERROR("11002"),
    ONE_OR_MORE_ENTITIES_NOT_FOUND("11003"),
    ENTITY_NOT_FOUND("11005");

    private final String code;

    ErrorCode(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

}


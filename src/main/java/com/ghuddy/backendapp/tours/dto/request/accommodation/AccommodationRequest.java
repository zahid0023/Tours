package com.ghuddy.backendapp.tours.dto.request.accommodation;

import lombok.Data;

@Data
public class AccommodationRequest {
    private Long accommodationTypeID;
    private String accommodationName;
    private String accommodationAddress;
}

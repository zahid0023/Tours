package com.ghuddy.backendapp.tours.dto.request.accommodation;

import lombok.Data;

@Data
public class AccommodationOptionDTOAddRequest {
    private Long tourPackageID;
    private AccommodationOptionDTO accommodationOptionDTO;
}

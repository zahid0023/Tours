package com.ghuddy.backendapp.tours.dto.request.accommodation;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageRequest;
import lombok.Data;

@Data
public class TourPackageAccommodationAddRequest {
    private Long tourPackageID;
    private TourPackageAccommodationRequest tourPackageAccommodation;
}

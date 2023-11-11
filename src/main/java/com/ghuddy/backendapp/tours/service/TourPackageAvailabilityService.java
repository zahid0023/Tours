package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageAvailabilitySetRequest;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageAvailabilityResponse;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;

public interface TourPackageAvailabilityService {
    TourPackageAvailabilityResponse generateTourPackageAvailabilityOptions(TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) throws TourPackageNotFoundException;
}

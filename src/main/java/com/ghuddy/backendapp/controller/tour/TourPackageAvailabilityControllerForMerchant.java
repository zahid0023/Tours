package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageAvailabilitySetRequest;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.service.TourPackageAvailabilityService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/merchant/")
public class TourPackageAvailabilityControllerForMerchant {
    private final TourPackageAvailabilityService tourPackageAvailabilityService;

    public TourPackageAvailabilityControllerForMerchant(TourPackageAvailabilityService tourPackageAvailabilityService) {
        this.tourPackageAvailabilityService = tourPackageAvailabilityService;
    }

    @RequestMapping(value = "/tour-package/availability/set", method = RequestMethod.POST)
    public ResponseEntity<?> setTourPackageAvailability(@RequestBody TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) {
        try {
            return new ResponseEntity<>(tourPackageAvailabilityService.generateTourPackageAvailabilityOptions(tourPackageAvailabilitySetRequest), HttpStatus.OK);
        } catch (TourPackageNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourPackageAvailabilitySetRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/tour-package/availability/check/component/option", method = RequestMethod.POST)
    public ResponseEntity<?> checkComponentOptions(@RequestBody TourPackageAvailabilitySetRequest tourPackageAvailabilitySetRequest) {
        try {
            return new ResponseEntity<>(tourPackageAvailabilityService.generateTourPackageAvailabilityOptions(tourPackageAvailabilitySetRequest), HttpStatus.OK);
        } catch (TourPackageNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourPackageAvailabilitySetRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }
}

package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageAvailabilitySetRequest;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.service.TourPackageAvailabilityService;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/merchant/")
//@Api(tags = "Tour - Tour Package Availability Controller For Admin", description = "This controller is used to manage tour package availability by merchants.")
public class TourPackageAvailabilityControllerForMerchant {
    private final TourPackageAvailabilityService tourPackageAvailabilityService;
    private final TourPackageService tourPackageService;

    public TourPackageAvailabilityControllerForMerchant(TourPackageAvailabilityService tourPackageAvailabilityService,
                                                        TourPackageService tourPackageService) {
        this.tourPackageAvailabilityService = tourPackageAvailabilityService;
        this.tourPackageService = tourPackageService;
    }

    @RequestMapping(path = "/tour-package/availability/get/all/components/by/{tour-package-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourPackageAllComponents(@PathVariable("tour-package-id") Long tourPackageId, @RequestParam String requestId) {
        try {
            TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(tourPackageId);
            return new ResponseEntity<>(tourPackageAvailabilityService.getAllComponentOptionsByTourPackage(tourPackageEntity, requestId), HttpStatus.OK);
        } catch (TourPackageNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
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

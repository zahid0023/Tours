package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryAddRequest;
import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.service.SpotEntryService;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/merchant")
public class SpotEntryControllerForMerchant {
    private final TourPackageService tourPackageService;
    private final SpotEntryService spotEntryService;

    public SpotEntryControllerForMerchant(TourPackageService tourPackageService, SpotEntryService spotEntryService) {
        this.tourPackageService = tourPackageService;
        this.spotEntryService = spotEntryService;
    }

    @RequestMapping(path = "/tour-package/spot/entry/add", method = RequestMethod.POST)
    public ResponseEntity addSpotEntry(@RequestBody SpotEntryAddRequest spotEntryAddRequest) {
        try {
            TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(spotEntryAddRequest.getTourPackageId());
            return new ResponseEntity(spotEntryService.addTourPackageSpotEntry(tourPackageEntity, spotEntryAddRequest.getSpotEntryRequest(), spotEntryAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourPackageNotFoundException ex) {
            return new ResponseEntity(new ErrorResponse(ex.getErrorCode(), spotEntryAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour-package/spot/entry/list/add", method = RequestMethod.POST)
    public ResponseEntity addSpotEntries(@RequestBody SpotEntryListAddRequest spotEntryListAddRequest) {
        try {
            TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(spotEntryListAddRequest.getTourPackageId());
            return new ResponseEntity(spotEntryService.addTourPackageSpotEntries(tourPackageEntity, spotEntryListAddRequest.getSpotEntryRequestList(), spotEntryListAddRequest.getRequestId()), HttpStatus.CREATED);
        } catch (TourPackageNotFoundException ex) {
            return new ResponseEntity(new ErrorResponse(ex.getErrorCode(), spotEntryListAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }
}

package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageOptionCapacityPriceFilterAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageOptionCapacityPriceSetRequest;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourPackageOptionCapacityPriceNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourPackageOptionNotFoundException;
import com.ghuddy.backendapp.tours.service.TourPackageOptionCapacityPriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/merchant")
//@Api(tags = "Tour - Tour Package Option Capacity Price Controller For Merchant", description = "This controller is used to manage tour package option price capacity by merchants.")
public class TourPackageOptionCapacityPriceForMerchantController {

    TourPackageOptionCapacityPriceService tourPackageOptionCapacityPriceService;

    TourPackageOptionCapacityPriceForMerchantController(TourPackageOptionCapacityPriceService tourPackageOptionCapacityPriceService) {
        this.tourPackageOptionCapacityPriceService = tourPackageOptionCapacityPriceService;
    }


    @RequestMapping(path = "/tour-package/option/capacity-price/set", method = RequestMethod.POST)
    public ResponseEntity<?> setTourPackageOptionCapacityPrice(@RequestBody TourPackageOptionCapacityPriceSetRequest request) {
        try {
            log.info(request.toString());
            return new ResponseEntity<>(tourPackageOptionCapacityPriceService.setCapacityPrice(request), HttpStatus.OK);
        } catch (TourPackageOptionNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), request.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour-package/option/capacity-price/filter/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageOptionCapacityPriceFilter(@RequestBody TourPackageOptionCapacityPriceFilterAddRequest request) {
        try {
            return new ResponseEntity<>(tourPackageOptionCapacityPriceService.addCapacityPriceFilter(request), HttpStatus.OK);
        } catch (TourPackageOptionCapacityPriceNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), request.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour-package/option/capacity-price/generate/{package-option-id}", method = RequestMethod.PUT)
    public ResponseEntity<?> generateCapacityPriceDaily(@PathVariable("package-option-id") Long packageOptionId, String requestId) {
        try {
            return new ResponseEntity<>(tourPackageOptionCapacityPriceService.generateAvailabilityCapacityPrice(packageOptionId, requestId), HttpStatus.OK);
        } catch (TourPackageOptionCapacityPriceNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/tour-package/option/capacity-price/get/package-option/{package-option-id}/for-range/{startDate}/{endDate}", method = RequestMethod.GET)
    public ResponseEntity<?> getPackageOptionCapacityPriceDailyByDateRange(@PathVariable("package-option-id") Long packageOptionId,
                                                                                                                           @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                                                                           @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                                                                                           String requestId) {
        try {
            return new ResponseEntity<>(tourPackageOptionCapacityPriceService.getTourPackageOptionCapacityPriceDailyList(packageOptionId, startDate, endDate, requestId), HttpStatus.OK);
        } catch (TourPackageOptionCapacityPriceNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/tour-package/option/capacity-price/filter/get-all/{package-option-id}/for-range/{startDate}/{endDate}", method = RequestMethod.GET)
    public ResponseEntity<?> getPackageOptionCapacityPriceFilterByDateRange(@PathVariable("package-option-id") Long packageOptionId,
                                                                         @PathVariable("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
                                                                         @PathVariable("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
                                                                         String requestId) {
        try {
            return new ResponseEntity<>(tourPackageOptionCapacityPriceService.getOptionCapacityPriceFilterList(packageOptionId, startDate, endDate, requestId), HttpStatus.OK);
        } catch (TourPackageOptionNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}

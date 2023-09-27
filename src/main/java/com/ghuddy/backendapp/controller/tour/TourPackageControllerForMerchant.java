package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/merchant")
//@Api(tags = "Tour - Tour Package Controller For Merchant", description = "This controller is used to manage tour packages by merchants.")
public class TourPackageControllerForMerchant {
    private final TourPackageService tourPackageService;

    public TourPackageControllerForMerchant(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @RequestMapping(path = "/tour-package/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTourPackageTypes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourPackageService.getAllTourPackageTypes(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(),requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour-package/type/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTourPackageTypesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourPackageService.getAllTourPackageTypesPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(),requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour-package/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackage(@RequestBody TourPackageAddRequest tourPackageAddRequest) throws TourNotFoundException {
        return new ResponseEntity<>(tourPackageService.addTourPackage(tourPackageAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/tour-package/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackage(@RequestBody TourPackageListAddRequest tourPackageListAddRequest) throws TourNotFoundException {
        return new ResponseEntity<>(tourPackageService.addTourPackages(tourPackageListAddRequest), HttpStatus.CREATED);
    }
}

package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.accommodation.*;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.service.AccommodationService;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import com.ghuddy.backendapp.tours.utils.RequestUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/merchant")
//@Api(tags = "Tour - Accommodation Controller For Merchant", description = "This controller is used to manage tour accommodation by merchants.")
public class AccommodationControllerForMerchant {
    private final AccommodationService accommodationService;
    private final TourPackageService tourPackageService;

    public AccommodationControllerForMerchant(AccommodationService accommodationService,
                                              TourPackageService tourPackageService) {
        this.accommodationService = accommodationService;
        this.tourPackageService = tourPackageService;
    }

    // room category
    @RequestMapping(path = "/room/category/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRoomCategories(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourRoomCategories(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(),requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/room/category/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRoomCategoriesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourRoomCategoriesPaginated(pageSize, pageNumber,requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(),requestId), HttpStatus.NOT_FOUND);
        }
    }

    // room type
    @RequestMapping(path = "/room/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRoomTypes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourRoomTypes(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(),requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/room/type/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllRoomTypesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourRoomTypesPaginated(pageSize, pageNumber,requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(),requestId), HttpStatus.NOT_FOUND);
        }
    }

    // accommodation
    @RequestMapping(path = "/accommodation/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAccommodations(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourAccommodations(requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(),requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/accommodation/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllAccommodationsPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(accommodationService.getAllTourAccommodationsPaginated(pageSize, pageNumber,requestId), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(),requestId), HttpStatus.NOT_FOUND);
        }
    }

    // Tour Package Accommodation
    @RequestMapping(path = "/tour-package/accommodation/option/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageAccommodation(@RequestBody AccommodationOptionAddRequest accommodationOptionAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(accommodationOptionAddRequest.getTourPackageID());
        return new ResponseEntity<>(accommodationService.addTourPackageAccommodation(tourPackageEntity, accommodationOptionAddRequest.getAccommodationOptionRequest(), accommodationOptionAddRequest.getRequestId()), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/tour-package/accommodation/option/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageAccommodations(@RequestBody AccommodationOptionListAddRequest accommodationOptionListAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(accommodationOptionListAddRequest.getTourPackageID());
        return new ResponseEntity<>(accommodationService.addTourPackageAccommodations(tourPackageEntity, accommodationOptionListAddRequest.getAccommodationOptionRequestList(), accommodationOptionListAddRequest.getRequestId()), HttpStatus.CREATED);
    }
}

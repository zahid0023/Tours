package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.transporation.TourPackageTransportationAddRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TourPackageTransportationListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import com.ghuddy.backendapp.tours.service.TransportationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/merchant")
//@Api(tags = "Tour - Tour Transportation Controller For Merchant", description = "This controller is used to manage tour transportation by merchants.")
public class TransportationControllerForMerchant {
    private final TransportationService transportationService;
    private final TourPackageService tourPackageService;

    public TransportationControllerForMerchant(TransportationService transportationService,
                                               TourPackageService tourPackageService) {
        this.transportationService = transportationService;
        this.tourPackageService = tourPackageService;
    }

    // transportation route
    @RequestMapping(path = "/transportation/route/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationRoutes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationRoutes(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/transportation/route/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationRoutesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationRoutesPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // transportation mode
    @RequestMapping(path = "/transportation/mode/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationModes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationModes(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/transportation/mode/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationModesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationModesPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // transportation brand
    @RequestMapping(path = "/transportation/brand/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationBrands(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationBrands(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.info(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/transportation/brand/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationBrandsPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationBrandsPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.info(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // transportation provider
    @RequestMapping(path = "/transportation/provider/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationProviders(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationProviders(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/transportation/provider/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationProvidersPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationProvidersPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            log.error("Error:" + ex);
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }


    // tour package transportation
    @RequestMapping(path = "/admin/tour-package/transportation/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageTransportation(@RequestBody TourPackageTransportationAddRequest tourPackageTransportationAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(tourPackageTransportationAddRequest.getTourPackageID());
        return new ResponseEntity<>(transportationService.addTourPackageTransportation(tourPackageEntity, tourPackageTransportationAddRequest.getTourPackageTransportation()), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/admin/tour-package/transportation/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageTransportation(@RequestBody TourPackageTransportationListAddRequest tourPackageTransportationListAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(tourPackageTransportationListAddRequest.getTourPackageID());
        return new ResponseEntity<>(transportationService.addTourPackageTransportations(tourPackageEntity, tourPackageTransportationListAddRequest), HttpStatus.CREATED);
    }
}

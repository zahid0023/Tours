package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageAvailabilitySetRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageOptionCheckRequest;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
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
    private final TourSubscriptionService tourSubscriptionService;

    public TourPackageControllerForMerchant(TourPackageService tourPackageService,
                                            TourSubscriptionService tourSubscriptionService) {
        this.tourPackageService = tourPackageService;
        this.tourSubscriptionService = tourSubscriptionService;
    }

    // tour package type
    @RequestMapping(path = "/tour-package/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTourPackageTypes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourPackageService.getAllTourPackageTypes(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour-package/type/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTourPackageTypesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourPackageService.getAllTourPackageTypesPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    // tour package

    @RequestMapping(path = "/tour-package/option/check", method = RequestMethod.POST)
    public ResponseEntity<?> checkTourPackageOptionsCombination(@RequestBody TourPackageOptionCheckRequest tourPackageOptionCheckRequest) {
        TourPackageEntity tourPackageEntity = new TourPackageEntity();
        tourPackageEntity.setTourPackageType(tourPackageService.getTourPackageTypeEntityByPackageTypeID(tourPackageOptionCheckRequest.getTourPackageTypeId()));
        try {
            log.info(tourPackageOptionCheckRequest.toString());
            return new ResponseEntity<>(tourPackageService.checkTourPackageOptionCombination(tourPackageEntity, tourPackageOptionCheckRequest), HttpStatus.OK);
        } catch (EmptyListException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourPackageOptionCheckRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour-package/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackage(@RequestBody TourPackageAddRequest tourPackageAddRequest) {
        try {
            return new ResponseEntity<>(tourPackageService.addTourPackage(
                    tourSubscriptionService.getSubscribedTourEntityById(tourPackageAddRequest.getSubscribedTourID()),
                    tourPackageAddRequest.getTourPackageRequest(),
                    tourPackageAddRequest.getRequestId()
            ), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourPackageAddRequest.getRequestId()), HttpStatus.BAD_REQUEST);
        } catch (EmptyListException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourPackageAddRequest.getRequestId()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/tour-package/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackages(@RequestBody TourPackageListAddRequest tourPackageListAddRequest) throws TourNotFoundException {
        try {
            return new ResponseEntity<>(tourPackageService.addTourPackages(
                    tourSubscriptionService.getSubscribedTourEntityById(tourPackageListAddRequest.getSubscribedTourID()),
                    tourPackageListAddRequest.getTourPackages(),
                    tourPackageListAddRequest.getRequestId()
            ), HttpStatus.CREATED);
        } catch (TourNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourPackageListAddRequest.getRequestId()), HttpStatus.BAD_REQUEST);
        } catch (EmptyListException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), tourPackageListAddRequest.getRequestId()), HttpStatus.BAD_REQUEST);
        }
    }

    @RequestMapping(value = "/tour-package/get/detail/by/{tour-package-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourPackageDetail(@PathVariable("tour-package-id") Long tourPackageId, String requestId) {
        try {
            return new ResponseEntity<>(tourPackageService.getTourPackageDetailByTourPackageId(tourPackageId, requestId), HttpStatus.OK);
        } catch (TourPackageNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/tour-package/summary/get/all/by/{subscribed-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTourPackagesBySubscribedTourId(@PathVariable("subscribed-tour-id") Long subscribedTourId, String requestId) {
        try {
            SubscribedTourEntity subscribedTourEntity = tourSubscriptionService.getSubscribedTourEntityById(subscribedTourId);
            return new ResponseEntity<>(tourPackageService.getTourPackageSummaryBySubscribedTourId(subscribedTourEntity, requestId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        } catch (EmptyListException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(value = "/tour-package/summary/get/core/option/all/by/{tour-package-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getAllCoreTourPackageOptionByTourPackageId(@PathVariable("tour-package-id") Long tourPackageId, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(tourPackageService.getTourPackageCoreOptionsByTourPackageId(tourPackageId, requestId), HttpStatus.OK);
        } catch (TourPackageNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}

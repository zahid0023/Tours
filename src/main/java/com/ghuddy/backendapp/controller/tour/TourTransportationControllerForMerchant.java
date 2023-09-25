package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.service.TransportationService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/merchant")
public class TourTransportationControllerForMerchant {
    private final TransportationService transportationService;

    public TourTransportationControllerForMerchant(TransportationService transportationService) {
        this.transportationService = transportationService;
    }

    @RequestMapping(path = "/transportation/route/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationRoutes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationRoutes(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/transportation/route/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTransportationRoutesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(transportationService.getAllTransportationRoutesPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.NOT_FOUND);
        }
    }
}

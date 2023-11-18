package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.es.service.ESTourService;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.service.TourService;
import com.ghuddy.backendapp.tours.service.TourSubscriptionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/open")
public class TourControllerForUser {
    private final ESTourService esTourService;
    private final TourService tourService;
    private final TourSubscriptionService tourSubscriptionService;

    public TourControllerForUser(TourService tourService,
                                 TourSubscriptionService tourSubscriptionService,
                                 ESTourService esTourService) {
        this.tourService = tourService;
        this.tourSubscriptionService = tourSubscriptionService;
        this.esTourService = esTourService;
    }

    @RequestMapping(path = "/tours/get/tour/details/by/{subscribed-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourDetails(@PathVariable("subscribed-tour-id") Long subscribedTourId, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(esTourService.getAllAvailableTourPackages(subscribedTourId, requestId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        } catch (EmptyListException ex) {

            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}

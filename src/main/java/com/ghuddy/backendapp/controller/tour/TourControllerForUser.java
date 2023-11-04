package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.es.service.ESTourService;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/open")
public class TourControllerForUser {
    private final ESTourService esTourService;

    public TourControllerForUser(ESTourService esTourService) {
        this.esTourService = esTourService;
    }

    @RequestMapping(path = "/tours/get/tour/by/{subscribed-tour-id}", method = RequestMethod.GET)
    public ResponseEntity<?> getTourByTourId(@PathVariable("subscribed-tour-id") Long subscribedTourId, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(esTourService.getSubscribedTourById(subscribedTourId, requestId), HttpStatus.OK);
        } catch (TourNotFoundException ex) {
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode(), requestId), HttpStatus.NOT_FOUND);
        }
    }
}

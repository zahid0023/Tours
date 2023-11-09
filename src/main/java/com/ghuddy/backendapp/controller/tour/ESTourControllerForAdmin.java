package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.es.service.ESTourService;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/admin")
public class ESTourControllerForAdmin {
    private final ESTourService esTourService;

    public ESTourControllerForAdmin(ESTourService esTourService) {
        this.esTourService = esTourService;
    }

    @RequestMapping(path = "/index",method = RequestMethod.POST)
    public ResponseEntity<?> indexOneTour(@RequestBody Long subscribedTourId, String requestId) throws TourNotFoundException {
        esTourService.indexESSubscribedTour(subscribedTourId, requestId);
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

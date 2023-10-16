package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionAddRequest;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionDTO;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/merchant")
public class ControllerDemo {
    @RequestMapping(path = "/tour-package/accommodation/option/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageAccommodation(@RequestBody AccommodationOptionDTO accommodationOptionAddRequest) {
        return null;
    }
}

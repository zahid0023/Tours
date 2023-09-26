package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeListAddRequest;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/admin")
//@Api(tags = "Tour - Tour Package Controller For Admin", description = "This controller is used to manage tour packages by admins.")
public class TourPackageControllerForAdmin {
    private final TourPackageService tourPackageService;

    public TourPackageControllerForAdmin(TourPackageService tourPackageService) {
        this.tourPackageService = tourPackageService;
    }

    @RequestMapping(path = "/tour-package/type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageType(@RequestBody TourPackageTypeAddRequest tourPackageTypeAddRequest) {
        return new ResponseEntity<>(tourPackageService.addTourPackageType(tourPackageTypeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/tour-package/type/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageTypes(@RequestBody TourPackageTypeListAddRequest tourPackageTypeListAddRequest) {
        return new ResponseEntity<>(tourPackageService.addTourPackageTypes(tourPackageTypeListAddRequest), HttpStatus.CREATED);
    }
}

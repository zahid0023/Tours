package com.example.ghuddytour2.controller;

import com.example.ghuddytour2.tours.dto.request.TourPackageAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourPackageListAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourPackageTypeAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourPackageTypeListAddRequest;
import com.example.ghuddytour2.tours.service.TourPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/admin")
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

    @RequestMapping(path = "/tour-package/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackage(@RequestBody TourPackageAddRequest tourPackageAddRequest) {
        return new ResponseEntity<>(tourPackageService.addTourPackage(tourPackageAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/tour-package/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackage(@RequestBody TourPackageListAddRequest tourPackageListAddRequest) {
        return new ResponseEntity<>(tourPackageService.addTourPackages(tourPackageListAddRequest), HttpStatus.CREATED);
    }
}

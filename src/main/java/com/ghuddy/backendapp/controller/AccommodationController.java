package com.ghuddy.backendapp.controller;

import com.ghuddy.backendapp.tours.dto.request.accommodation.*;
import com.ghuddy.backendapp.tours.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.service.AccommodationService;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1")
public class AccommodationController {
    private final AccommodationService accommodationService;
    private final TourPackageService tourPackageService;

    public AccommodationController(AccommodationService accommodationService,
                                   TourPackageService tourPackageService) {
        this.accommodationService = accommodationService;
        this.tourPackageService = tourPackageService;
    }

    // Room Category
    @RequestMapping(path = "/admin/room/category/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRoomCategory(@RequestBody RoomCategoryAddRequest roomCategoryAddRequest) {
        return new ResponseEntity<>(accommodationService.addRoomCategory(roomCategoryAddRequest), HttpStatus.OK);
    }

    @RequestMapping(path = "/admin/room/category/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRoomCategories(@RequestBody RoomCategoryListAddRequest roomCategoryListAddRequest) {
        return new ResponseEntity<>(accommodationService.addRoomCategories(roomCategoryListAddRequest), HttpStatus.OK);
    }

    // Room Type
    @RequestMapping(path = "/admin/room/type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRoomType(@RequestBody RoomTypeAddRequest roomTypeAddRequest) {
        return new ResponseEntity<>(accommodationService.addRoomType(roomTypeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/admin/room/type/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addRoomTypes(@RequestBody RoomTypeListAddRequest roomTypeListAddRequest) {
        return new ResponseEntity<>(accommodationService.addRoomTypes(roomTypeListAddRequest), HttpStatus.CREATED);
    }

    // Accommodation Type
    @RequestMapping(path = "/admin/accommodation/type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addAccommodationType(@RequestBody AccommodationTypeAddRequest accommodationTypeAddRequest) {
        return new ResponseEntity<>(accommodationService.addAccommodationType(accommodationTypeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/admin/accommodation/type/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addAccommodationTypes(@RequestBody AccommodationTypeListAddRequest accommodationTypeListAddRequest) {
        return new ResponseEntity<>(accommodationService.addAccommodationTypes(accommodationTypeListAddRequest), HttpStatus.CREATED);
    }

    // Accommodation
    @RequestMapping(path = "/admin/accommodation/add", method = RequestMethod.POST)
    public ResponseEntity<?> addAccommodation(@RequestBody AccommodationAddRequest accommodationAddRequest) {
        return new ResponseEntity<>(accommodationService.addAccommodation(accommodationAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/admin/accommodation/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addAccommodations(@RequestBody AccommodationListAddRequest accommodationListAddRequest) {
        return new ResponseEntity<>(accommodationService.addAccommodations(accommodationListAddRequest), HttpStatus.OK);
    }

    // Tour Package Accommodation
    @RequestMapping(path = "/admin/tour-package/accommodation/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageAccommodation(@RequestBody TourPackageAccommodationAddRequest tourPackageAccommodationAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageByPackageID(tourPackageAccommodationAddRequest.getTourPackageID());
        return new ResponseEntity<>(accommodationService.addTourPackageAccommodation(tourPackageEntity, tourPackageAccommodationAddRequest.getTourPackageAccommodation()), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/admin/tour-package/accommodation/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageAccommodations(@RequestBody TourPackageAccommodationListAddRequest tourPackageAccommodationListAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageByPackageID(tourPackageAccommodationListAddRequest.getTourPackageID());
        return new ResponseEntity<>(accommodationService.addTourPackageAccommodations(tourPackageEntity, tourPackageAccommodationListAddRequest.getTourPackageAccommodations()), HttpStatus.CREATED);
    }
}

package com.example.ghuddytour2.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1/admin")
public class ActivityControllerForAdmin {

    @RequestMapping(path = "/activity/type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addActivityType() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/type/get/{activityTypeID}", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityTypeByID(@PathVariable Long activityTypeID) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/type/get/all-activities", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivityType() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/type/get/all-activities-paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivityTypePaginated(@RequestParam int pageSize, @RequestParam int pageNumber) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/type/search", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityTypeByKeyword(@RequestParam String keyword) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/type/search/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivityTypeByKeywordPaginated(@RequestParam String keyword, @RequestParam int pageSize, @RequestParam int pageNumber) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @RequestMapping(path = "/activity/type/delete/{activityID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteActivityType(@PathVariable Long activityID) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

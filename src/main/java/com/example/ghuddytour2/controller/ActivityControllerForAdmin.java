package com.example.ghuddytour2.controller;

import com.example.ghuddytour2.tours.dto.request.activity.ActivityAddRequest;
import com.example.ghuddytour2.tours.dto.request.activity.ActivityListAddRequest;
import com.example.ghuddytour2.tours.dto.request.activity.ActivityTypeAddRequest;
import com.example.ghuddytour2.tours.dto.request.activity.ActivityTypeListAddRequest;
import com.example.ghuddytour2.tours.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
@RequestMapping(path = "/api/v1/admin")
public class ActivityControllerForAdmin {
    private final ActivityService activityService;

    public ActivityControllerForAdmin(ActivityService activityService) {
        this.activityService = activityService;
    }


    // Activity Type
    @RequestMapping(path = "/activity/type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addActivityType(@RequestBody ActivityTypeAddRequest activityTypeAddRequest) {
        return new ResponseEntity<>(activityService.addActivityType(activityTypeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/activity/type/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addActivityTypes(@RequestBody ActivityTypeListAddRequest activityTypeListAddRequest) {
        return new ResponseEntity<>(activityService.addActivityTypes(activityTypeListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/activity/type/get/{activityTypeID}", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityTypeByID(@PathVariable Long activityTypeID) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivityType() {
        return null;
    }

    @RequestMapping(path = "/activity/type/get/all-paginated", method = RequestMethod.GET)
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

    // Activity
    @RequestMapping(path = "/activity/add", method = RequestMethod.POST)
    public ResponseEntity<?> addActivity(@RequestBody ActivityAddRequest activityAddRequest) {
        return new ResponseEntity<>(activityService.addActivity(activityAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/activity/add-list", method = RequestMethod.POST)
    public ResponseEntity<?> addActivities(@RequestBody ActivityListAddRequest activityListAddRequest) {
        return new ResponseEntity<>(activityService.addActivities(activityListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/activity/get/{activityID}", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityByID(@PathVariable Long activityID) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivities() {
        return null;
    }

    @RequestMapping(path = "/activity/get/all-paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivitiesPaginated(@RequestParam int pageSize, @RequestParam int pageNumber) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/search", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityByKeyword(@RequestParam String keyword) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/search/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivitiesByKeywordPaginated(@RequestParam String keyword, @RequestParam int pageSize, @RequestParam int pageNumber) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


    @RequestMapping(path = "/activity/delete/{activityID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteActivity(@PathVariable Long activityID) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }


}

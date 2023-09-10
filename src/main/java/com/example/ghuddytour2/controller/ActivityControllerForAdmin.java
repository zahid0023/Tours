package com.example.ghuddytour2.controller;

import com.example.ghuddytour2.enums.ErrorCode;
import com.example.ghuddytour2.tours.dto.request.ActivityAddRequest;
import com.example.ghuddytour2.tours.dto.request.ActivityTypeAddRequest;
import com.example.ghuddytour2.tours.dto.response.ActivityTypeResponseList;
import com.example.ghuddytour2.tours.dto.response.ErrorResponse;
import com.example.ghuddytour2.tours.exception.ActivityTypeNotFoundException;
import com.example.ghuddytour2.tours.exception.EmptyListException;
import com.example.ghuddytour2.tours.service.ActivityService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.dao.DataIntegrityViolationException;
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

    @RequestMapping(path = "/activity/type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addActivityType(@RequestBody ActivityTypeAddRequest activityTypeAddRequest) {
        try {
            return new ResponseEntity<>(activityService.addActivityType(activityTypeAddRequest), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.ACTIVITY_TYPE_ALREADY_EXISTS), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "/activity/type/get/{activityTypeID}", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityTypeByID(@PathVariable Long activityTypeID) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivityType() {
        try {
            ActivityTypeResponseList activityTypeResponseList = activityService.getAllActivityTypes();
            log.info("Activities:" + activityTypeResponseList.toString());
            return new ResponseEntity<>(activityTypeResponseList, HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.NOT_FOUND);
        }
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
        try {
            return new ResponseEntity<>(activityService.addActivity(activityAddRequest), HttpStatus.CREATED);
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.ACTIVITY_ALREADY_EXISTS), HttpStatus.CONFLICT);
        } catch (ActivityTypeNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.ACTIVITY_TYPE_NOT_FOUND), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/activity/get/{activityID}", method = RequestMethod.GET)
    public ResponseEntity<?> getActivityByID(@PathVariable Long activityID) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/activity/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllActivities() {
        try {
            return new ResponseEntity<>(activityService.getAllActivities(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY), HttpStatus.NOT_FOUND);
        }
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

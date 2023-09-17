package com.ghuddy.backendapp.controller;

import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.dto.request.tour.TourAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tour.TourCreateRequest;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.TourResponseList;
import com.ghuddy.backendapp.tours.exception.ActivityNotFoundException;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.LocationNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.service.TourService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.ExampleObject;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "/api/v1")
public class TourControllerForAdmin {
    private final TourService tourService;

    public TourControllerForAdmin(TourService tourService) {
        this.tourService = tourService;
    }

    // ADD Tour
    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = AcknowledgeResponse.class,
                                            description = "Tour Added Successfully. You can now associate this tour with activities to create a new tour/make this tour available.")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content(
                                    mediaType = "application/json",
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "LocationNotFoundException",
                                            description = "When a location does not exist in the database. In this scenario you have to create a destination location first.",
                                            value = "{\"status\": \"LOCATION_NOT_FOUND\", \"statusCode\": \"00001\"}"
                                    )
                            )
                    ),
                    @ApiResponse(responseCode = "409",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "TourAlreadyExists",
                                            description = "When a tour already exists in the database. A unique tour is defined by destinationLocation+numberOfDays+numberOfNights." +
                                                    "When you get this error it means that there is already a tour in the database in the destination location for the number of days and number of nights",
                                            value = "{\"status\": \"TOUR_ALREADY_EXIST\", \"statusCode\": \"10001\"}"
                                    )
                            ))
            }
    )
    @Operation(summary = "This API is used to add a Tour", description = "This API is used to add a tour. " +
            "Adding tour just means that you are adding a tour in a place with " +
            "number of days and number of nights! " +
            "It will be a real tour when you create itineraries for the added tour i.e. " +
            "you map activities to a added tour")
    @RequestMapping(path = "/admin/tours/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTour(@RequestBody TourAddRequest tourAddRequest) {
        try {
            return new ResponseEntity<>(tourService.addTour(tourAddRequest), HttpStatus.OK);
        } catch (LocationNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.TOUR_ALREADY_EXIST), HttpStatus.CONFLICT);
        }
    }

    @RequestMapping(path = "/admin/tours/delete/{tourID}", method = RequestMethod.DELETE)
    public ResponseEntity<?> deleteTour(@PathVariable Long tourID) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/admin/tours/get-by-id/{tourID}", method = RequestMethod.GET)
    public ResponseEntity<?> getTour(@PathVariable Long tourID) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TourResponseList.class,
                                            description = "A list of all the tours in the database is returned")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "EmptyTourList",
                                            description = "When there are no tours in the database. In this scenario you have to add a tour first.",
                                            value = "{\"status\": \"LIST_IS_EMPTY\", \"statusCode\": \"11001\"}"
                                    )
                            )
                    )
            }
    )
    @Operation(summary = "This API is used to get all the Added Tours in the database",
            description = "This API does not return the created tours but the added tours. " +
                    "The tours returned by this API does not have any activities associated with it i.e. it does not have any itinerary. " +
                    "This API is mainly used to get all the added tours so that admin can associate activities with this tour and create a tour.")
    @RequestMapping(path = "/admin/tours/get-all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTours() {
        try {
            return new ResponseEntity<>(tourService.getAllTours(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.NOT_FOUND);
        }
    }

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TourResponseList.class,
                                            description = "A paginated list of all the tours in the database is returned")
                            )
                    ),
                    @ApiResponse(
                            responseCode = "404",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = ErrorResponse.class),
                                    examples = @ExampleObject(
                                            name = "EmptyTourList",
                                            description = "When there are no tours for the given pageSize and pageNumber in the database. In this scenario you have to add a tour first.",
                                            value = "{\"status\": \"LIST_IS_EMPTY\", \"statusCode\": \"11001\"}"
                                    )
                            )
                    )
            }
    )
    @Operation(summary = "This API is used to get all the Added Tours in the database in a paginated way",
            description = "This API does not return the created tours but the added tours. " +
                    "You have to provide pageSize and pageNumber to get the paginated tours. " +
                    "The tours returned by this API does not have any activities associated with it i.e. it does not have any itinerary. " +
                    "This API is mainly used to get all the added tours so that admin can associate activities with this tour and create a tour.")
    @RequestMapping(path = "/admin/tours/get-all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllToursPaginated(@RequestParam Integer pageSize, @RequestParam Integer pageNumber) {
        try {
            return new ResponseEntity<>(tourService.getAllToursPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/admin/tours/get-all-by-destination-location", method = RequestMethod.GET)
    public ResponseEntity<?> getAllToursByDestinationLocation(String locationName) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/admin/tours/get-all-by-destination-location/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllToursPaginatedByDestinationLocation(@RequestParam String locationName, @RequestParam int pageSize, @RequestParam int pageNumber) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    // Create Tour
    @Operation(summary = "This API is used to create a tour. ",
            description = "When you added the tour you did not assign any activity to the tour. " +
                    "A tour will be a real tour when you assign activities to the tour. " +
                    "This is the API where you assign activities to a previously added tour creating itinerary for the tour.")
    @RequestMapping(path = "/admin/tours/create", method = RequestMethod.POST)
    public ResponseEntity<?> createTour(@RequestBody TourCreateRequest tourCreateRequest) throws TourNotFoundException, ActivityNotFoundException {
        return new ResponseEntity<>(tourService.createTour(tourCreateRequest), HttpStatus.CREATED);
    }

}

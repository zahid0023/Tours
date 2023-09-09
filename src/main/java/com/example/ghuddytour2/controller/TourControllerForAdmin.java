package com.example.ghuddytour2.controller;

import com.example.ghuddytour2.enums.ErrorCode;
import com.example.ghuddytour2.tours.dto.request.TourAddRequest;
import com.example.ghuddytour2.tours.dto.response.ErrorResponse;
import com.example.ghuddytour2.tours.dto.response.TourAddResponse;
import com.example.ghuddytour2.tours.exception.LocationNotFoundException;
import com.example.ghuddytour2.tours.service.TourService;
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

    @ApiResponses(
            value = {
                    @ApiResponse(
                            responseCode = "200",
                            content = @Content(
                                    mediaType = MediaType.APPLICATION_JSON_VALUE,
                                    schema = @Schema(implementation = TourAddResponse.class),
                                    examples = @ExampleObject(
                                            name = "TourAddResponse",
                                            description = "Tour Added Successfully. You can now associate this tour with activities to create a new tour/make this tour available.",
                                            value = "{\"status\": \"Successful\", \"statusCode\": \"00000\"}"
                                    )
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
    @Operation(summary = "This API is used to add a Tour", description = "Ensure all essential tour fields are included; " +
            "those marked with an asterisk are mandatory.")
    @RequestMapping(path = "/admin/tours/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTour(@RequestBody TourAddRequest tourAddRequest) {
        try {
            return new ResponseEntity<>(tourService.addTour(tourAddRequest), HttpStatus.OK);
        } catch (LocationNotFoundException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ex.getErrorCode()), HttpStatus.NOT_FOUND);
        } catch (DataIntegrityViolationException ex) {
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

    @RequestMapping(path = "/admin/tours/get-all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllTours() {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/admin/tours/get-all-paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllToursPaginated(@RequestParam int pageSize, @RequestParam int pageNumber) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/admin/tours/get-all-by-destination-location", method = RequestMethod.GET)
    public ResponseEntity<?> getAllToursByDestinationLocation(String locationName) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }

    @RequestMapping(path = "/admin/tours/get-all-paginated-by-destination-location", method = RequestMethod.GET)
    public ResponseEntity<?> getAllToursPaginatedByDestinationLocation(@RequestParam String locationName, @RequestParam int pageSize, @RequestParam int pageNumber) {
        return new ResponseEntity<>(null, HttpStatus.OK);
    }
}

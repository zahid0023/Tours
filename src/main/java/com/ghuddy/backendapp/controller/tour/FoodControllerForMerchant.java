package com.ghuddy.backendapp.controller.tour;

import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionAddRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.ErrorResponse;
import com.ghuddy.backendapp.tours.enums.ErrorCode;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.service.FoodService;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Slf4j
@RestController
@RequestMapping(path = "/api/v1/merchant")
//@Api(tags = "Tour - Food Controller For Merchant", description = "This controller is used to manage tour foods by merchants.")
public class FoodControllerForMerchant {
    private final FoodService foodService;
    private final TourPackageService tourPackageService;

    public FoodControllerForMerchant(FoodService foodService,
                                     TourPackageService tourPackageService) {
        this.foodService = foodService;
        this.tourPackageService = tourPackageService;
    }

    // food item
    @RequestMapping(path = "/food/item/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllFoodItems(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(foodService.getAllFoodItems(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/food/item/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllFoodItemsPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(foodService.getAllFoodItemsPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    // meal type
    @RequestMapping(path = "/meal/type/get/all", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMealTypes(@RequestParam String requestId) {
        try {
            return new ResponseEntity<>(foodService.getAllMealTypes(), HttpStatus.OK);
        } catch (EmptyListException ex) {
            log.error(ex.toString());
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    @RequestMapping(path = "/meal/type/get/all/paginated", method = RequestMethod.GET)
    public ResponseEntity<?> getAllMealTypesPaginated(@RequestParam("page-size") Integer pageSize, @RequestParam("page-number") Integer pageNumber, @RequestParam String requestId) {
        try {
            return new ResponseEntity<>(foodService.getAllMealTypesPaginated(pageSize, pageNumber), HttpStatus.OK);
        } catch (EmptyListException ex) {
            ex.printStackTrace();
            return new ResponseEntity<>(new ErrorResponse(ErrorCode.LIST_IS_EMPTY, requestId), HttpStatus.NOT_FOUND);
        }
    }

    // meal package
    @RequestMapping(path = "/food/option/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageFoodOption(@RequestBody FoodOptionAddRequest foodOptionAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(foodOptionAddRequest.getTourPackageID());
        return new ResponseEntity<>(foodService.addTourPackageFoodOption(tourPackageEntity, foodOptionAddRequest.getFoodOptionRequest(), foodOptionAddRequest.getRequestId()), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/food/option/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addTourPackageFoodOptions(@RequestBody FoodOptionListAddRequest foodOptionListAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageEntityByPackageID(foodOptionListAddRequest.getTourPackageID());
        return new ResponseEntity<>(foodService.addTourPackageFoodOptions(tourPackageEntity, foodOptionListAddRequest.getFoodOptionRequestList(), foodOptionListAddRequest.getRequestId()), HttpStatus.CREATED);
    }
}

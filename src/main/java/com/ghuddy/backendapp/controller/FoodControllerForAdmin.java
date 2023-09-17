package com.ghuddy.backendapp.controller;

import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.service.FoodService;
import com.ghuddy.backendapp.tours.service.TourPackageService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/v1/admin")
public class FoodControllerForAdmin {
    private final FoodService foodService;
    private final TourPackageService tourPackageService;

    public FoodControllerForAdmin(FoodService foodService,
                                  TourPackageService tourPackageService) {
        this.foodService = foodService;
        this.tourPackageService = tourPackageService;
    }

    @RequestMapping(path = "/food/item/add", method = RequestMethod.POST)
    public ResponseEntity<?> addFoodItem(@RequestBody FoodItemAddRequest foodItemAddRequest) {
        return new ResponseEntity<>(foodService.addFoodItem(foodItemAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/food/item/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addFoodItems(@RequestBody FoodItemListAddRequest foodItemListAddRequest) {
        return new ResponseEntity<>(foodService.addFoodItems(foodItemListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/food/meal-type/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMealType(@RequestBody MealTypeAddRequest mealTypeAddRequest) {
        return new ResponseEntity<>(foodService.addMealType(mealTypeAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/food/meal-type/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMealTypes(@RequestBody MealTypeListAddRequest mealTypeListAddRequest) {
        return new ResponseEntity<>(foodService.addMealTypes(mealTypeListAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/food/meal-package/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMealPackage(@RequestBody MealPackageAddRequest mealPackageAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageByPackageID(mealPackageAddRequest.getTourPackageID());
        return new ResponseEntity<>(foodService.addTourPackageMealPackage(tourPackageEntity, mealPackageAddRequest.getMealPackage()), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/food/meal-package/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMealTypes(@RequestBody MealPackageListAddRequest mealPackageListAddRequest) {
        TourPackageEntity tourPackageEntity = tourPackageService.getTourPackageByPackageID(mealPackageListAddRequest.getTourPackageID());
        return new ResponseEntity<>(foodService.addTourPackageMealPackages(tourPackageEntity, mealPackageListAddRequest.getMealPackages()), HttpStatus.CREATED);
    }
}

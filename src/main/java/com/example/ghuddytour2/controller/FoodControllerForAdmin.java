package com.example.ghuddytour2.controller;

import com.example.ghuddytour2.tours.dto.request.*;
import com.example.ghuddytour2.tours.service.FoodService;
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

    public FoodControllerForAdmin(FoodService foodService) {
        this.foodService = foodService;
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
        return new ResponseEntity<>(foodService.addTourPackageMealPackage(mealPackageAddRequest), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/food/meal-package/list/add", method = RequestMethod.POST)
    public ResponseEntity<?> addMealTypes(@RequestBody MealPackageListAddRequest mealPackageListAddRequest) {
        return new ResponseEntity<>(foodService.addTourPackageMealPackages(mealPackageListAddRequest), HttpStatus.CREATED);
    }
}

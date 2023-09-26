package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.food.FoodItemListResponse;
import com.ghuddy.backendapp.tours.dto.response.food.MealTypeListResponse;
import com.ghuddy.backendapp.tours.model.entities.FoodItemEntity;
import com.ghuddy.backendapp.tours.model.entities.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.MealTypeEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.List;

public interface FoodService {
    // food item
    AcknowledgeResponse addFoodItem(FoodItemAddRequest foodItemAddRequest);

    AcknowledgeResponse addFoodItems(FoodItemListAddRequest foodItemListAddRequest);

    FoodItemEntity getFoodItemEntityByID(Long foodItemID);

    FoodItemListResponse getAllFoodItems() throws EmptyListException;

    FoodItemListResponse getAllFoodItemsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // meal type
    AcknowledgeResponse addMealType(MealTypeAddRequest mealTypeAddRequest);

    AcknowledgeResponse addMealTypes(MealTypeListAddRequest mealTypeListAddRequest);

    MealTypeEntity getMealTypeEntityByID(Long mealTypeID);

    MealTypeListResponse getAllMealTypes() throws EmptyListException;

    MealTypeListResponse getAllMealTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    AcknowledgeResponse addTourPackageMealPackage(TourPackageEntity tourPackageEntity, MealPackageRequest mealPackageRequest);

    AcknowledgeResponse addTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages);

    List<MealPackageEntity> setTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages);


}

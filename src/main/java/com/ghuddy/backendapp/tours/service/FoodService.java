package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.food.FoodItemListResponse;
import com.ghuddy.backendapp.tours.dto.response.food.MealTypeListResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.FoodItemEntity;
import com.ghuddy.backendapp.tours.model.entities.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.MealTypeEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;

import java.util.List;

public interface FoodService {
    // food item
    InsertAcknowledgeResponse addFoodItem(FoodItemAddRequest foodItemAddRequest);

    InsertAcknowledgeListResponse addFoodItems(FoodItemListAddRequest foodItemListAddRequest);

    FoodItemEntity getFoodItemEntityByID(Long foodItemID);

    FoodItemListResponse getAllFoodItems() throws EmptyListException;

    FoodItemListResponse getAllFoodItemsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // meal type
    InsertAcknowledgeResponse addMealType(MealTypeAddRequest mealTypeAddRequest);

    InsertAcknowledgeListResponse addMealTypes(MealTypeListAddRequest mealTypeListAddRequest);

    MealTypeEntity getMealTypeEntityByID(Long mealTypeID);

    MealTypeListResponse getAllMealTypes() throws EmptyListException;

    MealTypeListResponse getAllMealTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // meal package

    InsertAcknowledgeResponse addTourPackageMealPackage(TourPackageEntity tourPackageEntity, MealPackageRequest mealPackageRequest, String requestId);

    InsertAcknowledgeListResponse addTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages, String requestId);

    List<MealPackageEntity> setTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages);


}

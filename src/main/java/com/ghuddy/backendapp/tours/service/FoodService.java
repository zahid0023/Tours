package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.food.FoodItemListResponse;
import com.ghuddy.backendapp.tours.dto.response.food.FoodOptionCombinationCheckResponse;
import com.ghuddy.backendapp.tours.dto.response.food.MealTypeListResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.food.FoodItemEntity;
import com.ghuddy.backendapp.tours.model.entities.food.FoodOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.food.MealTypeEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface FoodService {
    // food item
    InsertAcknowledgeResponse addFoodItem(FoodItemAddRequest foodItemAddRequest);

    InsertAcknowledgeListResponse addFoodItems(FoodItemListAddRequest foodItemListAddRequest);

    FoodItemEntity getFoodItemEntityByID(Long foodItemID);
    Map<Long, FoodItemEntity> getFoodItemEntitiesByIDs(Set<Long> foodItemIDs);

    FoodItemListResponse getAllFoodItems() throws EmptyListException;

    FoodItemListResponse getAllFoodItemsPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // meal type
    InsertAcknowledgeResponse addMealType(MealTypeAddRequest mealTypeAddRequest);

    InsertAcknowledgeListResponse addMealTypes(MealTypeListAddRequest mealTypeListAddRequest);

    MealTypeEntity getMealTypeEntityByID(Long mealTypeID);

    Map<Long, MealTypeEntity> getMealTypeEntitiesByIDs(Set<Long> mealTypeIDs);

    MealTypeListResponse getAllMealTypes() throws EmptyListException;

    MealTypeListResponse getAllMealTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // meal package

    FoodOptionCombinationCheckResponse getAllMealsCombination(FoodOptionCombinationCheckRequest foodOptionCombinationCheckRequest) throws EmptyListException;

    InsertAcknowledgeResponse addTourPackageFoodOption(TourPackageEntity tourPackageEntity, FoodOptionRequest foodOptionRequest, String requestId) throws EmptyListException;

    InsertAcknowledgeListResponse addTourPackageFoodOptions(TourPackageEntity tourPackageEntity, List<FoodOptionRequest> foodOptionRequestList, String requestId) throws EmptyListException;

    List<FoodOptionEntity> setTourPackageFoodOptions(TourPackageEntity tourPackageEntity, List<FoodOptionRequest> foodOptionRequestList);
    Map<Long, MealPackageEntity> getMealPackageEntitiesByIds(Set<Long> mealPackageIds);


}

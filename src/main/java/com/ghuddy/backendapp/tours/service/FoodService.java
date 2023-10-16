package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.food.FoodItemListResponse;
import com.ghuddy.backendapp.tours.dto.response.food.MealTypeListResponse;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.model.entities.*;
import com.ghuddy.backendapp.tours.utils.EntityUtil;

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

    InsertAcknowledgeResponse addTourPackageFoodOption(TourPackageEntity tourPackageEntity, FoodOptionRequest foodOptionRequest, String requestId);

    InsertAcknowledgeListResponse addTourPackageFoodOptions(TourPackageEntity tourPackageEntity, List<FoodOptionRequest> foodOptionRequestList, String requestId);

    List<FoodOptionEntity> setTourPackageFoodOptions(TourPackageEntity tourPackageEntity, List<FoodOptionRequest> foodOptionRequestList);


}

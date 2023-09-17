package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.food.*;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.entities.*;

import java.util.List;

public interface FoodService {
    AcknowledgeResponse addFoodItem(FoodItemAddRequest foodItemAddRequest);

    AcknowledgeResponse addFoodItems(FoodItemListAddRequest foodItemListAddRequest);

    AcknowledgeResponse addMealType(MealTypeAddRequest mealTypeAddRequest);

    AcknowledgeResponse addMealTypes(MealTypeListAddRequest mealTypeListAddRequest);

    AcknowledgeResponse addTourPackageMealPackage(TourPackageEntity tourPackageEntity, MealPackageRequest mealPackageRequest);

    AcknowledgeResponse addTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages);

    List<MealPackageEntity> setTourPackageMealPackages(TourPackageEntity tourPackageEntity, List<MealPackageRequest> mealPackages);

    MealTypeEntity getMealTypeEntityByID(Long mealTypeID);

    FoodItemEntity getFoodItemEntityByID(Long foodItemID);
}

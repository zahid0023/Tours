package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.request.*;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.FoodItemEntity;
import com.example.ghuddytour2.tours.entities.MealTypeEntity;

public interface FoodService {
    AcknowledgeResponse addFoodItem(FoodItemAddRequest foodItemAddRequest);

    AcknowledgeResponse addFoodItems(FoodItemListAddRequest foodItemListAddRequest);

    AcknowledgeResponse addMealType(MealTypeAddRequest mealTypeAddRequest);

    AcknowledgeResponse addMealTypes(MealTypeListAddRequest mealTypeListAddRequest);

    AcknowledgeResponse addTourPackageMealPackage(MealPackageAddRequest mealPackageAddRequest);

    AcknowledgeResponse addTourPackageMealPackages(MealPackageListAddRequest mealPackageListAddRequest);

    MealTypeEntity getMealTypeEntityByID(Long mealTypeID);

    FoodItemEntity getFoodItemEntityByID(Long foodItemID);
}

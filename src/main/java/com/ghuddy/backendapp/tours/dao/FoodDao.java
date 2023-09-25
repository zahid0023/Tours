package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.dto.data.FoodItemData;
import com.ghuddy.backendapp.tours.dto.data.MealTypeData;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.List;

public interface FoodDao {
    List<FoodItemData> getAllFoodItems(Integer pageSize, Integer pageNumber) throws EmptyListException;
    List<MealTypeData> getAllMealTypes(Integer pageSize, Integer pageNumber) throws EmptyListException;
}

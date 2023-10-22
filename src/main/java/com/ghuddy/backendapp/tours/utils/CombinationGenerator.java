package com.ghuddy.backendapp.tours.utils;

import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import com.google.common.collect.Lists;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@Slf4j
public class CombinationGenerator {
    public static List<List<Object>> generateCombinations(List<List<Object>> lists) {
        return Lists.cartesianProduct(lists);
    }

    public static List<List<MealPackageRequest>> generateMealPackagesCombination(List<List<MealPackageRequest>> mealPackages) {
        return Lists.cartesianProduct(mealPackages);
    }
}










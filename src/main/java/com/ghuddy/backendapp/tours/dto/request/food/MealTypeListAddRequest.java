package com.ghuddy.backendapp.tours.dto.request.food;

import lombok.Data;

import java.util.List;

@Data
public class MealTypeListAddRequest {
    private List<MealTypeRequest> mealTypes;
}

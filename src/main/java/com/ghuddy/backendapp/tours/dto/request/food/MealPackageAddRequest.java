package com.ghuddy.backendapp.tours.dto.request.food;

import com.ghuddy.backendapp.dto.request.BaseRequest;
import com.ghuddy.backendapp.exception.AbstractException;
import lombok.Data;

import java.util.List;

@Data
public class MealPackageAddRequest extends BaseRequest {
    private Long tourPackageId;
    private MealPackageRequest mealPackageRequest;

    /**
     * @throws AbstractException
     */
    @Override
    public void validate() throws AbstractException {

    }
}

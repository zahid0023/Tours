package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequest;

import java.math.BigDecimal;

public interface TourPackagePriceService {
    BigDecimal perPersonDefaultPackagePrice(TourPackageRequest tourPackageRequest);

    BigDecimal perPersonPerAccommodationPackageTotalPrice(AccommodationPackageRequest accommodationPackageRequest);

    BigDecimal perPersonPerMealPackageTotalPrice(MealPackageRequest mealPackageRequest);

    BigDecimal perPersonPerTransportationPackageTotalPrice(TransportationPackageRequest transportationPackageRequest);
}

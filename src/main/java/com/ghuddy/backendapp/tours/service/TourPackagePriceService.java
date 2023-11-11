package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequestForAvailability;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequest;

import java.math.BigDecimal;

public interface TourPackagePriceService {
    BigDecimal perPersonDefaultPackagePrice(TourPackageRequest tourPackageRequest, Integer numberOfTravellers);

    BigDecimal perPersonAccommodationOptionPrice(AccommodationOptionRequest accommodationOptionRequest);

    BigDecimal perPersonFoodOptionPrice(FoodOptionRequest foodOptionRequest);

    BigDecimal perPersonTransferOptionPrice(TransferOptionRequest transferOptionRequest, int numberOfTravellers);

}

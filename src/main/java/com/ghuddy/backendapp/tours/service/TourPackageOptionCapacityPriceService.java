package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageOptionCapacityPriceFilterAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageOptionCapacityPriceSetRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageOptionCapacityPriceDailyDataListResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageOptionCapacityPriceFilterListResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageOptionCapacityPriceFilterResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageOptionCapacityPriceResponse;
import com.ghuddy.backendapp.tours.exception.TourPackageOptionCapacityPriceNotFoundException;
import com.ghuddy.backendapp.tours.exception.TourPackageOptionNotFoundException;

import java.time.LocalDate;

public interface TourPackageOptionCapacityPriceService {

    TourPackageOptionCapacityPriceResponse setCapacityPrice(TourPackageOptionCapacityPriceSetRequest request) throws TourPackageOptionNotFoundException;

    TourPackageOptionCapacityPriceFilterResponse addCapacityPriceFilter(TourPackageOptionCapacityPriceFilterAddRequest request) throws TourPackageOptionCapacityPriceNotFoundException;

    AcknowledgeResponse generateAvailabilityCapacityPrice(Long packageOptionId, String requestId) throws TourPackageOptionCapacityPriceNotFoundException;

    TourPackageOptionCapacityPriceDailyDataListResponse getTourPackageOptionCapacityPriceDailyList(
            long packageOptionId, LocalDate startDate, LocalDate endDate, String requestId) throws TourPackageOptionCapacityPriceNotFoundException;

    TourPackageOptionCapacityPriceFilterListResponse getOptionCapacityPriceFilterList(
            long packageOptionId,
            LocalDate startDate,
            LocalDate endDate,
            String requestId) throws TourPackageOptionNotFoundException;


}

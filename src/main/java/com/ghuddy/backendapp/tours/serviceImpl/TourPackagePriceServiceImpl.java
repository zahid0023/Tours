package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.food.FoodOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequest;
import com.ghuddy.backendapp.tours.service.TourPackagePriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

@Slf4j
@Service
public class TourPackagePriceServiceImpl implements TourPackagePriceService {
    /**
     * @param tourPackageRequest
     * @return
     */
    @Override
    public BigDecimal perPersonDefaultPackagePrice(TourPackageRequest tourPackageRequest, Integer numberOfTravellers) {
        List<AccommodationOptionRequest> accommodationOptionRequestList = tourPackageRequest.getAccommodationOptionRequestList();
        List<FoodOptionRequest> foodOptionRequestList = tourPackageRequest.getFoodOptionRequestList();
        List<TransferOptionRequest> transferOptionRequestList = tourPackageRequest.getTransferOptionRequestList();
        BigDecimal totalPackagePrice = BigDecimal.ZERO;
        if (accommodationOptionRequestList != null)
            totalPackagePrice.add(accommodationOptionRequestList.stream()
                    .filter(AccommodationOptionRequest::getIsDefault)
                    .map(accommodationOptionRequest -> perPersonAccommodationOptionPrice(accommodationOptionRequest))
                    .reduce(BigDecimal.ZERO, BigDecimal::add));

        if (foodOptionRequestList != null)
            totalPackagePrice.add(foodOptionRequestList.stream()
                    .filter(FoodOptionRequest::getIsDefault)
                    .map(foodOptionRequest -> perPersonFoodOptionPrice(foodOptionRequest))
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        if (transferOptionRequestList != null) {
            totalPackagePrice.add(transferOptionRequestList.stream()
                    .filter(TransferOptionRequest::getIsDefault)
                    .map(transferOptionRequest -> perPersonTransferOptionPrice(transferOptionRequest, numberOfTravellers))
                    .reduce(BigDecimal.ZERO, BigDecimal::add));
        }
        return totalPackagePrice;
    }

    @Override
    public BigDecimal perPersonAccommodationOptionPrice(AccommodationOptionRequest accommodationOptionRequest) {
        return accommodationOptionRequest.getTourPackageAccommodationRequestList().stream()
                .map(accommodationPackageRequest -> accommodationPackageRequest.getPerNightRoomPrice().divide(BigDecimal.valueOf(accommodationPackageRequest.getForPersons())))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * @param transferOptionRequest
     * @return
     */
    @Override
    public BigDecimal perPersonTransferOptionPrice(TransferOptionRequest transferOptionRequest, int numberOfTravellers) {
        return transferOptionRequest.getTransferPackageRequestList().stream()
                .map(transferPackageRequest -> {
                    int suitableForPersons = transferPackageRequest.getSuitableForPersons();
                    BigDecimal perVehiclePerTripPrice = transferPackageRequest.getTransferUnitPrice();
                    return perVehiclePerTripPrice.divide(BigDecimal.valueOf(suitableForPersons > numberOfTravellers ? numberOfTravellers : suitableForPersons));
                })
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * @param foodOptionRequest
     * @return
     */
    @Override
    public BigDecimal perPersonFoodOptionPrice(FoodOptionRequest foodOptionRequest) {
        return foodOptionRequest.getMealPackageRequestList().stream()
                .map(MealPackageRequest::getPerMealPackagePrice)
                .reduce(BigDecimal.ZERO, BigDecimal::add);
    }

    /**
     * @param transportationPackageRequest
     * @return
     */
    @Override
    public BigDecimal perPersonTransportationPrice(TransportationPackageRequest transportationPackageRequest) {
        return null;
    }
}

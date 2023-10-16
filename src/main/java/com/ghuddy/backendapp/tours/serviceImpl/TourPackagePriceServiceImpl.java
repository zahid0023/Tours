package com.ghuddy.backendapp.tours.serviceImpl;

import com.ghuddy.backendapp.tours.dto.request.accommodation.AccommodationPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.food.MealPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequest;
import com.ghuddy.backendapp.tours.service.TourPackagePriceService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Slf4j
@Service
public class TourPackagePriceServiceImpl implements TourPackagePriceService {
    /**
     * @param tourPackageRequest
     * @return
     */
    @Override
    public BigDecimal perPersonDefaultPackagePrice(TourPackageRequest tourPackageRequest) {
        BigDecimal perPersonDefaultPackagePrice = new BigDecimal(0);
        //BigDecimal perPersonTotalDefaultAccommodationPrice = calculateTotalDefaultAccommodationPricePerPerson(tourPackageRequest.getAccommodationPackages());
        //log.info(perPersonTotalDefaultAccommodationPrice.toString());
        //BigDecimal perPersonTotalDefaultMealPrice = calculateTotalDefaultMealPricePerPerson(tourPackageRequest.getMealPackages());
        //log.info(perPersonTotalDefaultMealPrice.toString());

        //return perPersonDefaultPackagePrice.add(perPersonTotalDefaultAccommodationPrice).add(perPersonTotalDefaultMealPrice);
        return null;
    }

    private BigDecimal calculateTotalDefaultAccommodationPricePerPerson(List<AccommodationPackageRequest> accommodationPackages) {
        BigDecimal totalAccommodationPriceForAllDefaultAccommodationPackagesPerPerson = accommodationPackages.stream()
                //.filter(AccommodationPackageRequest::getIsDefault)
                .map(accommodationPackageRequest -> perPersonPerAccommodationPackageTotalPrice(accommodationPackageRequest))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalAccommodationPriceForAllDefaultAccommodationPackagesPerPerson;
    }

    private BigDecimal calculateTotalDefaultMealPricePerPerson(List<MealPackageRequest> mealPackages) {
        BigDecimal totalMealPricePerPerson = mealPackages.stream()
                // .filter(MealPackageRequest::getIsDefault)
                .map(mealPackageRequest -> perPersonPerMealPackageTotalPrice(mealPackageRequest))
                .reduce(BigDecimal.ZERO, BigDecimal::add);
        return totalMealPricePerPerson;
    }

    /**
     * @param accommodationPackage
     * @return
     */
    @Override
    public BigDecimal perPersonPerAccommodationPackageTotalPrice(AccommodationPackageRequest accommodationPackage) {
        //BigDecimal perRoomPerNightPrice = accommodationPackage.getUnitPrice();
        Integer numberOfPersonStayingInRoom = accommodationPackage.getForPersons();
        //BigDecimal perRoomPerNightPerPersonPrice = perRoomPerNightPrice.divide(BigDecimal.valueOf(numberOfPersonStayingInRoom), 2, BigDecimal.ROUND_HALF_UP);
        //Integer numberOfNights = accommodationPackage.getNumberOfNights();
        // BigDecimal perPersonAccommodationPackagePrice = perRoomPerNightPerPersonPrice.multiply(BigDecimal.valueOf(numberOfNights));
        //return perPersonAccommodationPackagePrice;
        return null;
    }

    /**
     * @param mealPackageRequest
     * @return
     */
    @Override
    public BigDecimal perPersonPerMealPackageTotalPrice(MealPackageRequest mealPackageRequest) {
        // BigDecimal perMealPackagePrice = mealPackageRequest.getPerMealPrice();
        Integer numberOfMeals = mealPackageRequest.getNumberOfMeals();
        // BigDecimal mealPackageTotalPrice = perMealPackagePrice.multiply(BigDecimal.valueOf(numberOfMeals));
        // return mealPackageTotalPrice;
        return null;
    }

    /**
     * @return
     */
    @Override
    public BigDecimal perPersonPerTransportationPackageTotalPrice(TransportationPackageRequest transportationPackageRequest) {
        BigDecimal perTravelerPrice = transportationPackageRequest.getUnitPrice();
        return perTravelerPrice.multiply(BigDecimal.ONE);
    }

    /**
     * @param transferPackageRequest
     * @return
     */
    @Override
    public BigDecimal perPersonPerTransferPackageTotalPrice(TransferPackageRequest transferPackageRequest, Integer numberOfTravellers) {
        BigDecimal perDayPrice = transferPackageRequest.getTransferPricePerDay();
        //Integer numberOfDays = transferPackageRequest.getNumberOfDays();
        //BigDecimal perPersonPrice = (perDayPrice.multiply(BigDecimal.valueOf(numberOfDays))).divide(BigDecimal.valueOf(numberOfTravellers));
        //return perPersonPrice;
        return null;
    }
}

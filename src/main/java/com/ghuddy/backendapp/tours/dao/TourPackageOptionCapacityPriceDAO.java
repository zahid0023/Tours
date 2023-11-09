package com.ghuddy.backendapp.tours.dao;

import com.ghuddy.backendapp.tours.model.data.tourpackage.TourPackageOptionCapacityPriceDailyData;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TourPackageOptionCapacityPriceDAO {

    int updateTourPackageOptionCapacityPriceDaily(long packageOptionId);

    List<TourPackageOptionCapacityPriceDailyData> getRoomCategoryAvailabilityPriceDailyList(
            long packageOptionId, LocalDate startDate, LocalDate endDate);
}

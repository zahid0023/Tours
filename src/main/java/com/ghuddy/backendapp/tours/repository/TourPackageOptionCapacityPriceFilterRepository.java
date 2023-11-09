package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.enums.AvailabilityPricePolicyTypeEnum;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionCapacityPriceEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionCapacityPriceFilterEntity;
import org.springframework.data.repository.CrudRepository;

import java.time.LocalDate;
import java.util.List;

public interface TourPackageOptionCapacityPriceFilterRepository extends CrudRepository<TourPackageOptionCapacityPriceFilterEntity, Long> {

    TourPackageOptionCapacityPriceFilterEntity findByTourDateAndPolicyTypeAndCapacityPriceAndDeleted(
            LocalDate tourDate,
            AvailabilityPricePolicyTypeEnum policyType,
            TourPackageOptionCapacityPriceEntity capacityPrice,
            boolean deleted
    );

    List<TourPackageOptionCapacityPriceFilterEntity> findAllByCapacityPriceAndTourDateAfterAndTourDateBeforeAndDeleted(
            TourPackageOptionCapacityPriceEntity capacityPrice,
            LocalDate startDate,
            LocalDate endDat,
            boolean deleted
    );

}

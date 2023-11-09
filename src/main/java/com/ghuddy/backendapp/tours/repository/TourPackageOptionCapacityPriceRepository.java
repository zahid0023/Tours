package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionCapacityPriceEntity;
import org.springframework.data.repository.CrudRepository;

public interface TourPackageOptionCapacityPriceRepository extends CrudRepository<TourPackageOptionCapacityPriceEntity, Long> {
    TourPackageOptionCapacityPriceEntity findByIdAndDeleted(Long id, boolean deleted);
}

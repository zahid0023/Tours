package com.ghuddy.backendapp.tours.repository;

import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageOptionEntity;
import org.springframework.data.repository.CrudRepository;

public interface TourPackageOptionRepository extends CrudRepository<TourPackageOptionEntity, Long> {
    TourPackageOptionEntity findByIdAndDeleted(Long id, boolean deleted);

}

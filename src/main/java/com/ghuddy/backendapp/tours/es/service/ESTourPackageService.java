package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourPackageDocument;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;

import java.util.List;

public interface ESTourPackageService {
    Boolean indexAvailableTourPackages(TourEntity tourEntity, String requestId);
    List<ESTourPackageDocument> getAvailableTourPackagesByTourId(TourEntity tourEntity);
    Boolean indexAvailableTourPackagesOptionsCombinations(TourEntity tourEntity, String requestId);
    List<ESTourComponentOptionCombinationDocument> getAllComponentOptionsCombinations(TourPackageEntity tourPackageEntity);
}

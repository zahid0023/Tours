package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.es.dto.response.ESTourPackageResponse;
import com.ghuddy.backendapp.tours.es.model.data.ESTourPackageData;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourPackageDocument;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.tour.TourEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;

import java.util.List;
import java.util.Map;

public interface ESTourPackageService {
    Boolean indexAvailableTourPackages(TourEntity tourEntity, String requestId);

    Boolean indexAvailableTourPackagesOptionsCombinations(TourEntity tourEntity, String requestId);

    Map<Long, List<ESTourPackageData>> organizeAvailableTourPackagesByTourPackageType(List<ESTourPackageDocument> esTourPackageDocumentList, String requestId);

    ESTourPackageData getAvailableTourPackageDetailsById(Long availableTourPackageId) throws TourPackageNotFoundException;

    Map<Long, List<Long>> organizeAvailableTourPackagesIdsByTourType(List<ESTourPackageDocument> esTourPackageDocumentList, String requestId);

    List<ESTourPackageDocument> getAllAvailableTourPackagesByTourId(Long tourId, String requestId);

    ESTourPackageResponse getAvailableTourPackagesByTourId(Long tourId, String requestId);

}

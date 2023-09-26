package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageTypeListResponse;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageTypeEntity;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.Map;
import java.util.Set;

public interface TourPackageService {
    AcknowledgeResponse addTourPackageType(TourPackageTypeAddRequest tourPackageTypeAddRequest);

    AcknowledgeResponse addTourPackageTypes(TourPackageTypeListAddRequest tourPackageTypeListAddRequest);

    TourPackageTypeListResponse getAllTourPackageTypes() throws EmptyListException;

    TourPackageTypeListResponse getAllTourPackageTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    AcknowledgeResponse addTourPackage(TourPackageAddRequest tourPackageAddRequest);

    AcknowledgeResponse addTourPackages(TourPackageListAddRequest tourPackageListAddRequest);

    TourPackageEntity getTourPackageByPackageID(Long tourPackageID);

    TourPackageTypeEntity getTourPackageTypeByPackageTypeID(Long tourPackageTypeID);

    Map<Long, TourPackageTypeEntity> getTourPackageTypeByPackageTypeIDs(Set<Long> tourPackageTypeIDs);
}

package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.TourPackageTypeListResponse;
import com.ghuddy.backendapp.tours.exception.TourNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageTypeEntity;
import com.ghuddy.backendapp.tours.exception.EmptyListException;

import java.util.Map;
import java.util.Set;

public interface TourPackageService {
    // tour package type
    InsertAcknowledgeResponse addTourPackageType(TourPackageTypeAddRequest tourPackageTypeAddRequest);

    InsertAcknowledgeListResponse addTourPackageTypes(TourPackageTypeListAddRequest tourPackageTypeListAddRequest);

    TourPackageTypeEntity getTourPackageTypeEntityByPackageTypeID(Long tourPackageTypeID);

    Map<Long, TourPackageTypeEntity> getTourPackageTypeEntitiesByPackageTypeIDs(Set<Long> tourPackageTypeIDs);

    TourPackageTypeListResponse getAllTourPackageTypes() throws EmptyListException;

    TourPackageTypeListResponse getAllTourPackageTypesPaginated(Integer pageSize, Integer pageNumber) throws EmptyListException;

    // tour package
    InsertAcknowledgeResponse addTourPackage(TourPackageAddRequest tourPackageAddRequest) throws TourNotFoundException;

    InsertAcknowledgeListResponse addTourPackages(TourPackageListAddRequest tourPackageListAddRequest) throws TourNotFoundException;

    TourPackageEntity getTourPackageEntityByPackageID(Long tourPackageID);

}

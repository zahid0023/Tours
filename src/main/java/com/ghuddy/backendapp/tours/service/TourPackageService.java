package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.data.DefaultCombinationData;
import com.ghuddy.backendapp.tours.dto.data.TourPackageCoreComponentData;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageOptionCheckRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeAddRequest;
import com.ghuddy.backendapp.tours.dto.request.tourpackage.TourPackageTypeListAddRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.tourpackage.*;
import com.ghuddy.backendapp.tours.exception.EmptyListException;
import com.ghuddy.backendapp.tours.exception.TourPackageNotFoundException;
import com.ghuddy.backendapp.tours.model.entities.SubscribedTourEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageTypeEntity;

import java.util.List;
import java.util.Map;
import java.util.Optional;
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
    public InsertAcknowledgeResponse addTourPackage(SubscribedTourEntity subscribedTourEntity, TourPackageRequest tourPackageRequest, String requestId) throws EmptyListException;

    public InsertAcknowledgeListResponse addTourPackages(SubscribedTourEntity subscribedTourEntity, List<TourPackageRequest> tourPackageRequestList, String requestId) throws EmptyListException;

    public List<TourPackageEntity> setTourPackages(SubscribedTourEntity subscribedTourEntity, List<TourPackageRequest> tourPackages);

    TourPackageEntity getTourPackageEntityByPackageID(Long tourPackageID) throws TourPackageNotFoundException;

    ComponentCombinationResponse checkTourPackageOptionCombination(TourPackageEntity tourPackageEntity, TourPackageOptionCheckRequest tourPackageOptionCheckRequest) throws EmptyListException;

    Optional<List<TourPackageOptionEntity>> setTourPackageOptions(TourPackageEntity tourPackageEntity, TourPackageOptionCheckRequest tourPackageOptionCheckRequest);

    TourPackageDetailResponse getTourPackageDetailByTourPackageId(Long tourPackageId, String requestId) throws TourPackageNotFoundException;

    TourPackageSummaryListResponse getTourPackageSummaryBySubscribedTourId(SubscribedTourEntity subscribedTourEntity, String requestId) throws EmptyListException;

    // tour package options
    TourPackageOptionListResponse getTourPackageCoreOptionsByTourPackageId(Long tourPackageId, String requestId) throws TourPackageNotFoundException;

}

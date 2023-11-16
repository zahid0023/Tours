package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface SpotEntryService {
    InsertAcknowledgeResponse addTourPackageSpotEntry(TourPackageEntity tourPackageEntity, SpotEntryRequest spotEntryRequest, String requestId);

    InsertAcknowledgeListResponse addTourPackageSpotEntries(TourPackageEntity tourPackageEntity, List<SpotEntryRequest> spotEntryRequestList, String requestId);

    List<SpotEntryPackageEntity> setTourPackageSpotEntries(SpotEntryOptionEntity spotEntryOptionEntity, List<SpotEntryRequest> spotEntryRequestList);
    Map<Long, SpotEntryPackageEntity> getSpotEntryPackageEntitiesById(Set<Long> spotEntryPackageIds);

}

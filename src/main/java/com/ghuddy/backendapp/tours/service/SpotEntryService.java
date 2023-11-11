package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.spot.entry.SpotEntryRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;

import java.util.List;

public interface SpotEntryService {
    InsertAcknowledgeResponse addTourPackageSpotEntry(TourPackageEntity tourPackageEntity, SpotEntryRequest spotEntryRequest, String requestId);

    InsertAcknowledgeListResponse addTourPackageSpotEntries(TourPackageEntity tourPackageEntity, List<SpotEntryRequest> spotEntryRequestList, String requestId);

    List<SpotEntryEntity> setTourPackageSpotEntries(TourPackageEntity tourPackageEntity, List<SpotEntryRequest> spotEntryRequestList, String requestId);

}

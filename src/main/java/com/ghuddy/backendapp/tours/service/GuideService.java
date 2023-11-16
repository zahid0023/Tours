package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.guide.GuideOptionRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.GuidePackageEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface GuideService {
    InsertAcknowledgeResponse addTourPackageGuideOption();
    InsertAcknowledgeListResponse addTourPackageGuideOptions();
    List<GuideOptionEntity> setTourPackageGuideOptions(TourPackageEntity tourPackageEntity, List<GuideOptionRequest> guideOptionRequestList);
    Map<Long, GuidePackageEntity> getGuidePackageEntitiesById(Set<Long> guidePackageIds);
}

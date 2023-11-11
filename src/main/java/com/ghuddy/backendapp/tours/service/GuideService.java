package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.guide.GuideOptionRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;

import java.util.List;

public interface GuideService {
    InsertAcknowledgeResponse addTourPackageGuideOption();
    InsertAcknowledgeListResponse addTourPackageGuideOptions();
    List<GuideOptionEntity> setTourPackageGuideOptions(TourPackageEntity tourPackageEntity, List<GuideOptionRequest> guideOptionRequestList);
}

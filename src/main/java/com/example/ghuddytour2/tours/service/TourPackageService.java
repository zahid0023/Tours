package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.request.TourPackageTypeAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourPackageTypeListAddRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;

public interface TourPackageService {
    AcknowledgeResponse addTourPackageType(TourPackageTypeAddRequest tourPackageTypeAddRequest);

    AcknowledgeResponse addTourPackageTypes(TourPackageTypeListAddRequest tourPackageTypeListAddRequest);
}

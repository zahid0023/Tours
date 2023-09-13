package com.example.ghuddytour2.tours.service;

import com.example.ghuddytour2.tours.dto.request.TourPackageAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourPackageListAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourPackageTypeAddRequest;
import com.example.ghuddytour2.tours.dto.request.TourPackageTypeListAddRequest;
import com.example.ghuddytour2.tours.dto.response.AcknowledgeResponse;
import com.example.ghuddytour2.tours.entities.TourPackageEntity;
import com.example.ghuddytour2.tours.entities.TourPackageTypeEntity;

public interface TourPackageService {
    AcknowledgeResponse addTourPackageType(TourPackageTypeAddRequest tourPackageTypeAddRequest);

    AcknowledgeResponse addTourPackageTypes(TourPackageTypeListAddRequest tourPackageTypeListAddRequest);

    AcknowledgeResponse addTourPackage(TourPackageAddRequest tourPackageAddRequest);

    AcknowledgeResponse addTourPackages(TourPackageListAddRequest tourPackageListAddRequest);

    TourPackageEntity getTourPackageByPackageID(Long tourPackageID);

    TourPackageTypeEntity getTourPackageTypeByPackageTypeID(Long tourPackageTypeID);
}

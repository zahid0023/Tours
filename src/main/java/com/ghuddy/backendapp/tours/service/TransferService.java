package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.transfer.TransferPackageRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TourPackageTransportationListAddRequest;
import com.ghuddy.backendapp.tours.dto.request.transporation.TransportationPackageRequest;
import com.ghuddy.backendapp.tours.dto.response.AcknowledgeResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.TransportationPackageEntity;

import java.util.List;

public interface TransferService {
    // tour package transportation
    InsertAcknowledgeResponse addTourPackageTransferPackage(TourPackageEntity tourPackageEntity, TransferPackageRequest transferPackageRequest);

    InsertAcknowledgeListResponse addTourPackageTransferPackages(TourPackageEntity tourPackageEntity, TourPackageTransportationListAddRequest tourPackageTransportationListAddRequest);

    List<TransportationPackageEntity> setTourPackageTransferPackages(TourPackageEntity tourPackageEntity, List<TransportationPackageRequest> tourPackageTransportations);
}

package com.ghuddy.backendapp.tours.service;

import com.ghuddy.backendapp.tours.dto.request.transfer.TransferOptionRequest;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeListResponse;
import com.ghuddy.backendapp.tours.dto.response.InsertAcknowledgeResponse;
import com.ghuddy.backendapp.tours.model.entities.TourPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.TransferOptionEntity;

import java.util.List;

public interface TransferService {
    // tour package transportation
    InsertAcknowledgeResponse addTourPackageTransferOption(TourPackageEntity tourPackageEntity, TransferOptionRequest transferOptionRequest, String requestId);

    InsertAcknowledgeListResponse addTourPackageTransferOptions(TourPackageEntity tourPackageEntity, List<TransferOptionRequest> transferOptionRequestList, String requestId);

    List<TransferOptionEntity> setTourPackageTransferOptions(TourPackageEntity tourPackageEntity, List<TransferOptionRequest> transferOptionRequestList);
}

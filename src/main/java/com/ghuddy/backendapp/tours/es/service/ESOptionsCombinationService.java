package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.es.dto.data.ESTourPackagePriceCalculationData;
import com.ghuddy.backendapp.tours.es.dto.response.ESComponentCombinationCheckResponse;
import com.ghuddy.backendapp.tours.es.model.entities.ESTourComponentOptionCombinationDocument;

import java.util.List;

public interface ESOptionsCombinationService {

    List<ESTourComponentOptionCombinationDocument> getAllComponentCombinationsByAvailableTourPackageId(Long availableTourPackageId, String requestId);
    List<Long> getAllComponentCombinationIds(List<ESTourComponentOptionCombinationDocument> esTourComponentOptionCombinationDocumentList, String requestId);
    ESComponentCombinationCheckResponse getComponentCombinationPrice(ESTourPackagePriceCalculationData esTourPackagePriceCalculationData, String requestId);

}

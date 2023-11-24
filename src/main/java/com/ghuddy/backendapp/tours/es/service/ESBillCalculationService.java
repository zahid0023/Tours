package com.ghuddy.backendapp.tours.es.service;

import com.ghuddy.backendapp.tours.es.dto.data.ESTourPackagePriceCalculationData;
import com.ghuddy.backendapp.tours.es.dto.response.ESBillCalculationResponse;

import java.io.IOException;
import java.util.List;

public interface ESBillCalculationService {
    ESBillCalculationResponse calculateBill(List<ESTourPackagePriceCalculationData> esTourPackagePriceCalculationDataList) throws IOException;
}

package com.ghuddy.backendapp.tours.es.dto.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.es.dto.data.ESTourPackagePriceCalculationData;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ESBillCalculationRequest {
    @Schema(description = "The list of tour packages with options whose price have to be calculated", required = true)
    @JsonProperty("tour_cart")
    private List<ESTourPackagePriceCalculationData> esTourPackagePriceCalculationDataList;
}

package com.ghuddy.backendapp.tours.model.data.transportation;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TransportationBrandData {
    @Schema(description = "The id of the transportation brand", example = "1")
    private Long transportationBrandId;
    @Schema(description = "The brand name of the transportation", example = "Hyundai")
    private String transportationBrandName;
}

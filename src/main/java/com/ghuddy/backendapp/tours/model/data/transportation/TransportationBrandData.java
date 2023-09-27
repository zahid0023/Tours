package com.ghuddy.backendapp.tours.model.data.transportation;

import com.ghuddy.backendapp.tours.model.entities.TransportationBrandEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class TransportationBrandData {
    @Schema(description = "The id of the transportation brand", example = "1")
    private Long transportationBrandId;
    @Schema(description = "The brand name of the transportation", example = "Hyundai")
    private String transportationBrandName;

    public TransportationBrandData(TransportationBrandEntity transportationBrandEntity) {
        this.transportationBrandId = transportationBrandEntity.getId();
        this.transportationBrandName = transportationBrandEntity.getBrandName();
    }
}

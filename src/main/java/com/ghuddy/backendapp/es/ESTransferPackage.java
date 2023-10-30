package com.ghuddy.backendapp.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.model.entities.TransferPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ESTransferPackage {
    @JsonProperty("tour_package_transfer_route")
    private String transferRoute;
    @JsonProperty("tour_package_transportation_mode_name")
    private String transportationModeName;
    @JsonProperty("tour_package_transportation_provider_name")
    private String transportationProviderName;
    @JsonProperty("is_ac")
    private Boolean isAc;
    @JsonProperty("trip_type")
    private TripType tripType;
    @JsonProperty("per_vehicle_per_trip_price")
    private BigDecimal unitPrice;
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;

    public ESTransferPackage(TransferPackageEntity transferPackageEntity){
        this.transferRoute = transferPackageEntity.getTransferRoute();
        this.transportationModeName = transferPackageEntity.getTransportationModeEntity().getModeName();
        this.transportationProviderName = transferPackageEntity.getTransportationProviderEntity().getTransportationProviderName();
        this.isAc = transferPackageEntity.getIsAc();
        this.tripType = transferPackageEntity.getTripType();
        this.unitPrice = transferPackageEntity.getUnitPrice();
        this.suitableForPersons = transferPackageEntity.getSuitableForPersons();
    }
}

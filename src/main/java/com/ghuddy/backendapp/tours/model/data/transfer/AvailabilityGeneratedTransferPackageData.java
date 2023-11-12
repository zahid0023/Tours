package com.ghuddy.backendapp.tours.model.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvailabilityGeneratedTransferPackageData {
    @Schema(description = "The id of the transfer package")
    @JsonProperty("tour_package_transfer_package_id")
    private Long transferPackageId;
    @Schema(description = "The route where the transfer service will be provided")
    @JsonProperty("tour_package_transfer_route")
    private String transferRoute;
    @Schema(description = "The name of the transportation mode")
    @JsonProperty("tour_package_transportation_mode_name")
    private String transportationModeName;
    @Schema(description = "The name of the transportation provider")
    @JsonProperty("tour_package_transportation_provider_name")
    private String transportationProviderName;
    @Schema(description = "Where is ac")
    @JsonProperty("is_ac")
    private Boolean isAc;
    @Schema(description = "The type of the trip")
    @JsonProperty("trip_type")
    private TripType tripType;
    @Schema(description = "per day price of the transportation")
    @JsonProperty("per_vehicle_per_trip_price")
    private BigDecimal unitPrice;
    @Schema(description = "maximum number of travellers in a vehicle")
    @JsonProperty("suitable_for_persons")
    private Integer suitableForPersons;

    private AvailabilityGeneratedTransferPackageData(TransferPackageData transferPackageData){
        this.transferPackageId = transferPackageData.getTransferPackageId();
        this.transferRoute = transferPackageData.getTransferRoute();
        this.transportationModeName = transferPackageData.getTransportationModeName();
        this.transportationProviderName = transferPackageData.getTransportationProviderName();
        this.isAc = transferPackageData.getIsAc();
        this.tripType = transferPackageData.getTripType();
        this.suitableForPersons = transferPackageData.getSuitableForPersons();
    }

    public AvailabilityGeneratedTransferPackageData(AvailabilityGeneratedTransferPackageEntity availabilityGeneratedTransferPackageEntity){
        this(new TransferPackageData(availabilityGeneratedTransferPackageEntity.getTransferPackageEntity()));
        this.unitPrice = availabilityGeneratedTransferPackageEntity.getPerVehiclePerTripPrice();
    }

    public AvailabilityGeneratedTransferPackageData(TransferPackageEntity transferPackageEntity){
        this(new TransferPackageData(transferPackageEntity));
        this.unitPrice = transferPackageEntity.getUnitPrice();
    }
}

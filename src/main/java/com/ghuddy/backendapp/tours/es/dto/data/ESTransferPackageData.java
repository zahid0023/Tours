package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.model.data.transfer.AvailabilityGeneratedTransferPackageData;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferPackageEntity;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.math.BigDecimal;

@Data
public class ESTransferPackageData {
    @JsonProperty("tour_package_available_transfer_package_id")
    @Field(name = "tour_package_available_transfer_package_id")
    private Long availableTransferPackageId;

    @JsonProperty("tour_package_available_transfer_route")
    @Field(name = "tour_package_available_transfer_route")
    private String transferRoute;
    @JsonProperty("tour_package_available_transportation_mode_name")
    @Field(name = "tour_package_available_transportation_mode_name")
    private String transportationModeName;
    @JsonProperty("tour_package_available_transportation_provider_name")
    @Field(name = "tour_package_available_transportation_provider_name")
    private String transportationProviderName;
    @JsonProperty("is_ac")
    @Field(name="is_ac")
    private Boolean isAc;
    @JsonProperty("trip_type")
    @Field(name = "trip_type")
    private TripType tripType;
    @JsonProperty("per_vehicle_per_trip_price")
    @Field(name = "per_vehicle_per_trip_price")
    private BigDecimal unitPrice;
    @JsonProperty("suitable_for_persons")
    @Field(name = "suitable_for_persons")
    private Integer suitableForPersons;

    public ESTransferPackageData(AvailabilityGeneratedTransferPackageEntity availabilityGeneratedTransferPackageEntity){
        TransferPackageEntity transferPackageEntity = availabilityGeneratedTransferPackageEntity.getTransferPackageEntity();
        this.availableTransferPackageId = availabilityGeneratedTransferPackageEntity.getId();
        this.transferRoute = transferPackageEntity.getTransferRoute();
        this.transportationModeName = transferPackageEntity.getTransportationModeEntity().getModeName();
        this.transportationProviderName = transferPackageEntity.getTransportationProviderEntity().getTransportationProviderName();
        this.isAc = transferPackageEntity.getIsAc();
        this.tripType = transferPackageEntity.getTripType();
        this.unitPrice = transferPackageEntity.getUnitPrice();
        this.suitableForPersons = transferPackageEntity.getSuitableForPersons();
    }
}

package com.ghuddy.backendapp.tours.model.data.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.transportation.AvailabilityGeneratedTransportationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class AvailabilityGeneratedTransportationPackageData extends OptionData {
    @Schema(description = "The transportation package id")
    @JsonProperty("tour_package_transportation_package_id")
    private Long transportationPackageId;
    @Schema(description = "The name of the route associated with this tour package transportation package")
    @JsonProperty("tour_package_transportation_route_name")
    private String transportationRouteName;
    @Schema(description = "The name of the transportation mode associated with this tour package transportation package")
    @JsonProperty("tour_package_transportation_mode_name")
    private String transportationModeName;
    @Schema(description = "The name of the transportation brand associated with this tour package transportation package")
    @JsonProperty("tour_package_transportation_brand_name")
    private String transportationBrandName;
    @Schema(description = "The name of the transportation provider associated with this tour package transportation package")
    @JsonProperty("tour_package_transportation_provider_name")
    private String transportationProviderName;
    @Schema(description = "Is the trip round or one way")
    @JsonProperty("trip_type")
    private TripType tripType;
    @Schema(description = "Whether ac or not")
    @JsonProperty("is_ac")
    private boolean isAc;
    @Schema(description = "The price of this transportation package", required = true, example = "120")
    @JsonProperty("transportation_package_unit_price")
    private BigDecimal unitPrice;

    private AvailabilityGeneratedTransportationPackageData(TransportationPackageData transportationPackageData) {
        this.transportationPackageId = transportationPackageData.getTransportationPackageId();
        this.transportationRouteName = transportationPackageData.getTransportationRouteName();
        this.transportationModeName = transportationPackageData.getTransportationModeName();
        this.transportationBrandName = transportationPackageData.getTransportationBrandName();
        this.transportationProviderName = transportationPackageData.getTransportationProviderName();
        this.tripType = transportationPackageData.getTripType();
        this.isAc = transportationPackageData.isAc();
    }

    public AvailabilityGeneratedTransportationPackageData(AvailabilityGeneratedTransportationPackageEntity availabilityGeneratedTransportationPackageEntity) {
        this(new TransportationPackageData(availabilityGeneratedTransportationPackageEntity.getTransportationPackageEntity(),true));
        this.unitPrice = availabilityGeneratedTransportationPackageEntity.getTransportationPackagePrice();
        this.setIsActive(availabilityGeneratedTransportationPackageEntity.getIsActive());
    }

    public AvailabilityGeneratedTransportationPackageData(TransportationPackageEntity transportationPackageEntity) {
        this(new TransportationPackageData(transportationPackageEntity, transportationPackageEntity.getIsActive()));
        this.unitPrice = transportationPackageEntity.getPerPersonTransportationPackagePrice();
        this.setIsActive(transportationPackageEntity.getIsActive());
    }
}

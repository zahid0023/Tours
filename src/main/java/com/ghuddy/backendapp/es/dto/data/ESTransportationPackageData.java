package com.ghuddy.backendapp.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.enums.TripType;
import com.ghuddy.backendapp.tours.model.entities.TransportationPackageEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class ESTransportationPackageData{
    @JsonProperty("tour_package_transportation_package_id")
    private Long transportationPackageId;
    @JsonProperty("tour_package_transportation_route_id")
    private Long transportationRouteId;
    @JsonProperty("tour_package_transportation_route_name")
    private String transportationRouteName;
    @JsonProperty("tour_package_transportation_mode_id")
    private Long transportationModeId;
    @JsonProperty("tour_package_transportation_mode_name")
    private String transportationModeName;
    @JsonProperty("tour_package_transportation_brand_id")
    private Long transportationBrandId;
    @JsonProperty("tour_package_transportation_brand_name")
    private String transportationBrandName;
    @JsonProperty("tour_package_transportation_provider_id")
    private Long transportationProviderId;
    @JsonProperty("tour_package_transportation_provider_name")
    private String transportationProviderName;
    @JsonProperty("trip_type")
    private TripType tripType;
    @JsonProperty("is_ac")
    private boolean isAc;
    @JsonProperty("transportation_package_unit_price")
    private BigDecimal unitPrice;

    public ESTransportationPackageData(TransportationPackageEntity transportationPackageEntity) {
        this.transportationPackageId = transportationPackageEntity.getId();
        this.transportationRouteId = transportationPackageEntity.getTransportationRouteEntity().getId();
        this.transportationRouteName = transportationPackageEntity.getTransportationRouteEntity().getSourceLocation().getPlaceName() + " - " + transportationPackageEntity.getTransportationRouteEntity().getDestinationLocation().getPlaceName();
        this.transportationModeId = transportationPackageEntity.getTransportationModeEntity().getId();
        this.transportationModeName = transportationPackageEntity.getTransportationModeEntity().getModeName();
        this.transportationBrandId = transportationPackageEntity.getTransportationBrandEntity().getId();
        this.transportationBrandName = transportationPackageEntity.getTransportationBrandEntity().getBrandName();
        this.transportationProviderId = transportationPackageEntity.getTransportationProviderEntity().getId();
        this.transportationProviderName = transportationPackageEntity.getTransportationProviderEntity().getTransportationProviderName();
        this.tripType = transportationPackageEntity.getTripType();
        this.isAc = transportationPackageEntity.getIsAc();
        this.unitPrice = transportationPackageEntity.getPerPersonTransportationPackagePrice();
    }
}

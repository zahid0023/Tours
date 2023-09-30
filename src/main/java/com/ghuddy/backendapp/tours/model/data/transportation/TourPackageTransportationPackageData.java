package com.ghuddy.backendapp.tours.model.data.transportation;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TourPackageTransportationEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class TourPackageTransportationPackageData {
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
    private String tripType;
    @Schema(description = "Whether ac or not")
    @JsonProperty("is_ac")
    private boolean isAc;

    public TourPackageTransportationPackageData(TourPackageTransportationEntity tourPackageTransportationEntity) {
        this.transportationRouteName = tourPackageTransportationEntity.getTransportationRouteEntity().getSourceLocation().getPlaceName() + " - " + tourPackageTransportationEntity.getTransportationRouteEntity().getDestinationLocation().getPlaceName();
        this.transportationModeName = tourPackageTransportationEntity.getTransportationModeEntity().getModeName();
        this.transportationBrandName = tourPackageTransportationEntity.getTransportationBrandEntity().getBrandName();
        this.transportationProviderName = tourPackageTransportationEntity.getTransportationProviderEntity().getTransportationProviderName();
        this.tripType = tourPackageTransportationEntity.getTripType().getDisplayName();
        this.isAc = tourPackageTransportationEntity.getIsAc();
    }
}

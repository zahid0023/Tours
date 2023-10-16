package com.ghuddy.backendapp.tours.model.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TransferPackageEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class TransferPackageData {
    @Schema(description = "The id of the transfer package")
    @JsonProperty("tour_package_transfer_package_id")
    private Long transferPackageId;
    @Schema(description = "The name of the transportation mode")
    @JsonProperty("tour_package_transportation_mode_name")
    private String transportationModeName;
    @Schema(description = "The name of the transportation provider")
    @JsonProperty("tour_package_transportation_provider_name")
    private String transportationProviderName;
    @Schema(description = "Where is ac")
    @JsonProperty("is_ac")
    private Boolean isAc;
    @Schema(description = "per day price of the transportation")
    @JsonProperty("tour_package_transfer_package_price_per_day")
    private BigDecimal perDayPrice;

    @Schema(description = "The number of days the transfer will be provided")
    @JsonProperty("tour_package_transfer_provided_in_days")
    private Integer[] dayNumbers;
    @Schema(description = "maximum number of travellers in a vehicle")
    @JsonProperty("maximum_number_of_travellers")
    private Integer maximumNumberOfTravellers;
    @Schema(description = "total number of vehicles")
    @JsonProperty("number_of_vehicles")
    private Integer numberOfVehicles;
    @Schema(description = "per person transfer package price")
    @JsonProperty("tour_package_transfer_package_price_per_person")
    private BigDecimal perPersonTransferPackagePrice;

    public TransferPackageData(TransferPackageEntity transferPackageEntity) {
        this.transferPackageId = transferPackageEntity.getId();
        this.transportationModeName = transferPackageEntity.getTransportationModeEntity().getModeName();
        this.transportationProviderName = transferPackageEntity.getTransportationProviderEntity().getTransportationProviderName();
        this.isAc = transferPackageEntity.getIsAc();
        this.perDayPrice = transferPackageEntity.getPerDayPrice();
        this.dayNumbers = transferPackageEntity.getDayNumbers();
        this.maximumNumberOfTravellers = transferPackageEntity.getMaximumNumberOfTravellers();
        this.numberOfVehicles = transferPackageEntity.getNumberOfVehicles();
        this.perPersonTransferPackagePrice = transferPackageEntity.getPerPersonTransferPackagePrice();
        // this.isDefault = transferPackageEntity.getIsIncluded();
    }
}

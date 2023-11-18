package com.ghuddy.backendapp.tours.model.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AvailabilityGeneratedTransferOptionData extends OptionData {
    @Schema(description = "The transfer option id")
    @JsonProperty("tour_package_transfer_option_id")
    private Long transferOptionId;

    @Schema(description = "The transfer packages")
    @JsonProperty("tour_package_transfer_packages")
    List<AvailabilityGeneratedTransferPackageData> availabilityGeneratedTransferPackageDataList;

    public AvailabilityGeneratedTransferOptionData(TransferOptionEntity transferOptionEntity) {
        this.transferOptionId = transferOptionEntity.getId();
        this.setIsActive(transferOptionEntity.getIsActive());
        this.availabilityGeneratedTransferPackageDataList = transferOptionEntity.getTransferPackageEntities().stream()
                .map(transferPackageEntity -> new AvailabilityGeneratedTransferPackageData(transferPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(transferOptionEntity.getPerPersonTransferOptionPrice());
        this.setIsActive(transferOptionEntity.getIsActive());
    }

    public AvailabilityGeneratedTransferOptionData(AvailabilityGeneratedTransferOptionEntity availabilityGeneratedTransferOptionEntity) {
        this.transferOptionId = availabilityGeneratedTransferOptionEntity.getId();
        this.availabilityGeneratedTransferPackageDataList = availabilityGeneratedTransferOptionEntity.getAvailabilityGeneratedTransferPackageEntities().stream()
                .map(availabilityGeneratedTransferPackageEntity -> new AvailabilityGeneratedTransferPackageData(availabilityGeneratedTransferPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(availabilityGeneratedTransferOptionEntity.getTotalOptionPricePerPerson());
        this.setIsActive(availabilityGeneratedTransferOptionEntity.getIsActive());
    }
}

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

    private AvailabilityGeneratedTransferOptionData(TransferOptionData transferOptionData) {
        this.transferOptionId = transferOptionData.getTransferOptionId();

    }

    public AvailabilityGeneratedTransferOptionData(TransferOptionEntity transferOptionEntity) {
        this(new TransferOptionData(transferOptionEntity, transferOptionEntity.getIsActive()));
        this.availabilityGeneratedTransferPackageDataList = transferOptionEntity.getTransferPackageEntities().stream()
                .map(transferPackageEntity -> new AvailabilityGeneratedTransferPackageData(transferPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(transferOptionEntity.getPerPersonTransferOptionPrice());
        this.setIsActive(transferOptionEntity.getIsActive());
    }
}

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
    @JsonProperty("transfer_option_id")
    private Long transferOptionId;

    List<AvailabilityGeneratedTransferPackageData> availabilityGeneratedTransferPackageDataList;

    private AvailabilityGeneratedTransferOptionData(TransferOptionData transferOptionData){
        this.transferOptionId = transferOptionData.getTransferOptionId();

    }

    public AvailabilityGeneratedTransferOptionData(AvailabilityGeneratedTransferOptionEntity availabilityGeneratedTransferOptionEntity) {
        this(new TransferOptionData(availabilityGeneratedTransferOptionEntity.getTransferOptionEntity(),true));
        this.availabilityGeneratedTransferPackageDataList = availabilityGeneratedTransferOptionEntity.getAvailabilityGeneratedTransferPackageEntities().stream()
                .map(availabilityGeneratedTransferPackageEntity -> new AvailabilityGeneratedTransferPackageData(availabilityGeneratedTransferPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(availabilityGeneratedTransferOptionEntity.getTotalOptionPricePerPerson());

    }

    public AvailabilityGeneratedTransferOptionData(TransferOptionEntity transferOptionEntity){
        this(new TransferOptionData(transferOptionEntity,transferOptionEntity.getIsActive()));
        this.availabilityGeneratedTransferPackageDataList = transferOptionEntity.getTransferPackageEntities().stream()
                .map(transferPackageEntity -> new AvailabilityGeneratedTransferPackageData(transferPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(transferOptionEntity.getPerPersonTransferOptionPrice());
    }
}

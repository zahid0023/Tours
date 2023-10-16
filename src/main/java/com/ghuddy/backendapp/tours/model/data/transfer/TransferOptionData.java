package com.ghuddy.backendapp.tours.model.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TransferOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TransferOptionData {
    @Schema(description = "The transfer packages belonging to this tour package")
    @JsonProperty("transfer_packages")
    private List<TransferPackageData> transferPackageDataList;

    public TransferOptionData(TransferOptionEntity transferOptionEntity) {
        this.transferPackageDataList = transferOptionEntity.getTransferPackageEntities().stream()
                .map(transferPackageEntity -> new TransferPackageData(transferPackageEntity))
                .collect(Collectors.toList());
    }
}

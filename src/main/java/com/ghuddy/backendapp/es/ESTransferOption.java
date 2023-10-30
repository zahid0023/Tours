package com.ghuddy.backendapp.es;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TransferOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class ESTransferOption extends ESOption {
    @Schema(description = "The transfer packages belonging to this tour package")
    @JsonProperty("transfer_packages")
    private List<ESTransferPackage> transferPackageList;

    public ESTransferOption(TransferOptionEntity transferOptionEntity) {
        this.transferPackageList = transferOptionEntity.getTransferPackageEntities().stream()
                .map(transferPackageEntity -> new ESTransferPackage(transferPackageEntity))
                .toList();
        this.setDefault(transferOptionEntity.getIsDefault());
        this.setIsActive(transferOptionEntity.getActive());
        this.setTotalOptionPricePerPerson(transferOptionEntity.getPerPersonTransferOptionPrice());
    }
}

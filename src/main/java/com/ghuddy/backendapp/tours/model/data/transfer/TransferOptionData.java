package com.ghuddy.backendapp.tours.model.data.transfer;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.TransferOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;
import java.util.stream.Collectors;

@Data
public class TransferOptionData {

    @Schema(description = "The tour package id")
    @JsonProperty("tour_package_id")
    private Long tourPackageId;
    @Schema(description = "The transfer option id")
    @JsonProperty("transfer_option_id")
    private Long transferOptionId;
    @Schema(description = "Whether this is the default option for the package")
    @JsonProperty("tour_package_default_transfer_option")
    private Boolean isDefaultOption;
    @Schema(description = "The transfer packages belonging to this tour package")
    @JsonProperty("transfer_packages")
    private List<TransferPackageData> transferPackageDataList;

    public TransferOptionData(TransferOptionEntity transferOptionEntity) {
        this.tourPackageId = transferOptionEntity.getTourPackageEntity().getId();
        this.transferOptionId = transferOptionEntity.getId();
        this.transferPackageDataList = transferOptionEntity.getTransferPackageEntities().stream()
                .map(transferPackageEntity -> new TransferPackageData(transferPackageEntity))
                .collect(Collectors.toList());
        this.isDefaultOption = transferOptionEntity.getIsDefault();
    }
}

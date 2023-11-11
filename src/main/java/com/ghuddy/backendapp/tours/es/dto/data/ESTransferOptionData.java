package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Data
public class ESTransferOptionData extends ESOptionData {
    @Schema(description = "The transfer packages belonging to this tour package")
    @JsonProperty("transfer_packages")
    @Field(name = "transfer_packages")
    private List<ESTransferPackageData> transferPackageList;

    public ESTransferOptionData(TransferOptionEntity transferOptionEntity) {
        this.transferPackageList = transferOptionEntity.getTransferPackageEntities().stream()
                .map(transferPackageEntity -> new ESTransferPackageData(transferPackageEntity))
                .toList();
        //this.setDefault(transferOptionEntity.getIsDefault());
        //this.setIsActive(transferOptionEntity.getActive());
        this.setTotalOptionPricePerPerson(transferOptionEntity.getPerPersonTransferOptionPrice());
    }
}

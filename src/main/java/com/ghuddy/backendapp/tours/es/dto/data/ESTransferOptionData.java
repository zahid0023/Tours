package com.ghuddy.backendapp.tours.es.dto.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import org.springframework.data.elasticsearch.annotations.Field;

import java.util.List;

@Data
public class ESTransferOptionData {
    @JsonProperty("tour_package_available_transfer_option_id")
    @Field(name = "tour_package_available_transfer_option_id")
    private Long availableTransferOptionId;
    @Schema(description = "The transfer packages belonging to this tour package")
    @JsonProperty("tour_package_available_transfer_packages")
    @Field(name = "tour_package_available_transfer_packages")
    private List<ESTransferPackageData> transferPackageList;

    public ESTransferOptionData(AvailabilityGeneratedTransferOptionEntity availabilityGeneratedTransferOptionEntity) {
        availableTransferOptionId = availabilityGeneratedTransferOptionEntity.getId();
        this.transferPackageList = availabilityGeneratedTransferOptionEntity.getAvailabilityGeneratedTransferPackageEntities().stream()
                .map(availabilityGeneratedTransferPackageEntity -> new ESTransferPackageData(availabilityGeneratedTransferPackageEntity))
                .toList();
    }
}

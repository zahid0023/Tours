package com.ghuddy.backendapp.tours.es.model;

import com.ghuddy.backendapp.tours.model.entities.transfer.AvailabilityGeneratedTransferOptionEntity;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

import java.util.List;

@Getter
@Setter
public class ESTransferOptionDocument {

    @Field(name = "tour_package_available_transfer_option_id", type = FieldType.Long)
    private Long availableTransferOptionId;

    @Field(name = "tour_package_available_transfer_packages", type = FieldType.Nested, includeInParent = true)
    private List<ESTransferPackageDocument> transferPackageList;

    public ESTransferOptionDocument(AvailabilityGeneratedTransferOptionEntity availabilityGeneratedTransferOptionEntity) {
        availableTransferOptionId = availabilityGeneratedTransferOptionEntity.getId();
        this.transferPackageList = availabilityGeneratedTransferOptionEntity.getAvailabilityGeneratedTransferPackageEntities().stream()
                .map(availabilityGeneratedTransferPackageEntity -> new ESTransferPackageDocument(availabilityGeneratedTransferPackageEntity))
                .toList();
    }
}

package com.ghuddy.backendapp.tours.model.data.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AvailabilityGeneratedSpotEntryOptionData extends OptionData {
    @Schema(description = "The id of the tour package spot entry option")
    @JsonProperty("tour_package_spot_entry_option_id")
    private Long tourPackageSpotEntryOptionId;
    @Schema(description = "The spot entry list")
    @JsonProperty("tour_package_spot_entries")
    private List<SpotEntryPackageData> spotEntryPackageDataList;

    public AvailabilityGeneratedSpotEntryOptionData(SpotEntryOptionEntity spotEntryOptionEntity) {
        this.tourPackageSpotEntryOptionId = spotEntryOptionEntity.getId();
        this.spotEntryPackageDataList = spotEntryOptionEntity.getSpotEntryPackageEntities().stream()
                .map(spotEntryPackageEntity -> new SpotEntryPackageData(spotEntryPackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(spotEntryOptionEntity.getTotalOptionPricePerPerson());
        this.setIsActive(spotEntryOptionEntity.getIsActive());

    }
}

package com.ghuddy.backendapp.tours.model.data.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityData;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpotEntryData {
    @Schema(description = "The activity for which spot entry is required")
    @JsonProperty("activity")
    private ActivityData activityData;
    @Schema(description = "The per person price for this spot entry")
    @JsonProperty("spot_entry_price_per_person")
    private BigDecimal pricePerPerson;

    @Schema(description = "The remark")
    @JsonProperty("remark")
    private String remark;

    public SpotEntryData(SpotEntryEntity spotEntryEntity) {
        this.activityData = new ActivityData(spotEntryEntity.getActivityEntity());
        this.pricePerPerson = spotEntryEntity.getPricePerPerson();
        this.remark = spotEntryEntity.getRemark();
    }
}

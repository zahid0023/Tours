package com.ghuddy.backendapp.tours.model.data.spot.entry;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.data.activity.ActivityData;
import com.ghuddy.backendapp.tours.model.entities.spot.entry.SpotEntryEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class SpotEntryData extends OptionData {
    @Schema(description = "The activity for which spot entry is required")
    @JsonProperty("activity")
    private ActivityData activityData;
    @Schema(description = "The remark")
    @JsonProperty("remark")
    private String remark;

    public SpotEntryData(SpotEntryEntity spotEntryEntity, Boolean isActive) {
        super(isActive, spotEntryEntity.getPricePerPerson());
        this.activityData = new ActivityData(spotEntryEntity.getActivityEntity());
        this.remark = spotEntryEntity.getRemark();
    }
}

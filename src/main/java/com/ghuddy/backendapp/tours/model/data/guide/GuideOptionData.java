package com.ghuddy.backendapp.tours.model.data.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class GuideOptionData extends OptionData {
    @Schema(description = "Guide option id")
    @JsonProperty("tour_package_guide_option_id")
    private Long tourPackageGuideOptionId;
    @Schema(description = "guide packages")
    @JsonProperty("guide_packages")
    private List<GuidePackageData> guidePackageDataList;

    public GuideOptionData(GuideOptionEntity guideOptionEntity, Boolean isActive) {
        super(isActive, guideOptionEntity.getTotalOptionPrice());
        this.tourPackageGuideOptionId = guideOptionEntity.getId();
        this.guidePackageDataList = guideOptionEntity.getGuidePackageEntities().stream()
                .map(guidePackageEntity -> new GuidePackageData(guidePackageEntity))
                .toList();
    }
}

package com.ghuddy.backendapp.tours.model.data.guide;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.guide.AvailabilityGeneratedGuideOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.util.List;

@Data
public class AvailabilityGeneratedGuideOptionData extends OptionData {
    @Schema(description = "Guide option id")
    @JsonProperty("tour_package_guide_option_id")
    private Long tourPackageGuideOptionId;
    @Schema(description = "guide packages")
    @JsonProperty("guide_packages")
    private List<AvailabilityGeneratedGuidePackageData> availabilityGeneratedGuidePackageDataList;

    public AvailabilityGeneratedGuideOptionData(GuideOptionEntity guideOptionEntity){
        this.tourPackageGuideOptionId = guideOptionEntity.getId();
        this.availabilityGeneratedGuidePackageDataList = guideOptionEntity.getGuidePackageEntities().stream()
                .map(guidePackageEntity -> new AvailabilityGeneratedGuidePackageData(guidePackageEntity))
                .toList();
        this.setTotalOptionPricePerPerson(guideOptionEntity.getTotalOptionPrice());
        this.setIsActive(guideOptionEntity.getIsActive());
    }
}

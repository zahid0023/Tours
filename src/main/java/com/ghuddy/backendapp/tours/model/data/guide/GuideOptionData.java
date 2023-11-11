package com.ghuddy.backendapp.tours.model.data.guide;

import com.ghuddy.backendapp.tours.model.data.OptionData;
import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import lombok.Data;

import java.util.List;

@Data
public class GuideOptionData extends OptionData {
    private Long tourPackageGuideOptionId;
    private List<GuidePackageData> guidePackageDataList;

    public GuideOptionData(GuideOptionEntity guideOptionEntity, Boolean isActive, Boolean isDefault) {
        super(isActive, isDefault, guideOptionEntity.getTotalOptionPrice());
        this.tourPackageGuideOptionId = guideOptionEntity.getId();
        this.guidePackageDataList = guideOptionEntity.getGuidePackageEntities().stream()
                .map(guidePackageEntity -> new GuidePackageData(guidePackageEntity))
                .toList();
    }
}

package com.ghuddy.backendapp.tours.model.data.guide;

import com.ghuddy.backendapp.tours.model.entities.guide.GuidePackageEntity;
import lombok.Data;

import java.math.BigDecimal;

@Data
public class GuidePackageData {
    private Long guidePackageId;
    private Integer dayNumber;
    private Integer numberOfGuidesForDay;
    private BigDecimal totalGuidePriceForDay;

    public GuidePackageData(GuidePackageEntity guidePackageEntity) {
        this.guidePackageId = guidePackageEntity.getId();
        this.dayNumber = guidePackageEntity.getDayNumber();
        this.numberOfGuidesForDay = guidePackageEntity.getNumberOfGuideForDay();
        this.totalGuidePriceForDay = guidePackageEntity.getTotalGuidePriceForDay();
    }
}

package com.ghuddy.backendapp.tours.model.entities.guide;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_guide_packages")
public class AvailabilityGeneratedGuidePackageEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guide_package_id")
    private GuidePackageEntity guidePackageEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_guide_option_id")
    private AvailabilityGeneratedGuideOptionEntity availabilityGeneratedGuideOptionEntity;
    @NotNull
    @Column(name = "total_guide_package_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalGuidePackagePrice;


    @NotNull
    @Column(name = "day_number", nullable = false)
    private Integer dayNumber;

    @NotNull
    @Column(name = "number_of_guides_for_day", nullable = false)
    private Integer numberOfGuidesForDay;

}
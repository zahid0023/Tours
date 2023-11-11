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
    private AvailabilityGeneratedGuideOptionEnttiy availabilityGeneratedGuideOptionEnttiy;
    @NotNull
    @Column(name = "total_guide_package_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalGuidePackagePrice;



}
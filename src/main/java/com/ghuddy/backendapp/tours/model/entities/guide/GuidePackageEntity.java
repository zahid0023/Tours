package com.ghuddy.backendapp.tours.model.entities.guide;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.guide.GuideOptionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "guide_packages")
public class GuidePackageEntity extends BaseEntity {
    @NotNull
    @Column(name = "day_number", nullable = false)
    private Integer dayNumber;

    @Column(name = "number_of_guide_for_day")
    private Integer numberOfGuideForDay;

    @Column(name = "total_guide_price_for_day", precision = 10, scale = 2)
    private BigDecimal totalGuidePriceForDay;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "guide_option_id")
    private GuideOptionEntity guideOptionEntity;

    @OneToMany(mappedBy = "guidePackageEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedGuidePackageEntity> availabilityGeneratedGuidePackageEntities = new ArrayList<>();

}
package com.ghuddy.backendapp.tours.model.entities.guide;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageEntity;
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
@Table(name = "tour_package_guide_options")
public class GuideOptionEntity extends BaseEntity {
    @NotNull
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "tour_package_id", nullable = false)
    private TourPackageEntity tourPackageEntity;

    @Column(name = "total_option_price", precision = 10, scale = 2)
    private BigDecimal totalOptionPrice; // per person

    @OneToMany(mappedBy = "guideOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<GuidePackageEntity> guidePackageEntities = new ArrayList<>();

}
package com.ghuddy.backendapp.tours.model.entities.guide;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.AvailabilityGeneratedTourPackageAllOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.AvailabilityGeneratedTourPackageInclusiveOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailabilityGeneratedTourPackageEntity;
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
@Table(name = "availability_generated_guide_options")
public class AvailabilityGeneratedGuideOptionEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_tour_package_id")
    private AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity;
    @OneToMany(mappedBy = "availabilityGeneratedGuideOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedGuidePackageEntity> availabilityGeneratedGuidePackageEntities = new ArrayList<>();
    @NotNull
    @Column(name = "total_option_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalOptionPricePerPerson;

    @Column(name = "is_active")
    private Boolean isActive;

    @OneToMany(mappedBy = "availabilityGeneratedGuideOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedTourPackageAllOptionEntity> availabilityGeneratedTourPackageAllOptionEntities = new ArrayList<>();

    @OneToMany(mappedBy = "availabilityGeneratedGuideOptionEntity", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedTourPackageInclusiveOptionEntity> availabilityGeneratedTourPackageInclusiveOptionEntities = new ArrayList<>();

}
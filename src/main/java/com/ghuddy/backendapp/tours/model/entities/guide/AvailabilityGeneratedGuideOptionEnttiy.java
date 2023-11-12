package com.ghuddy.backendapp.tours.model.entities.guide;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageAvailabilityEntity;
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
public class AvailabilityGeneratedGuideOptionEnttiy extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_availability_id")
    private TourPackageAvailabilityEntity tourPackageAvailabilityEntity;
    @OneToMany(mappedBy = "availabilityGeneratedGuideOptionEnttiy", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<AvailabilityGeneratedGuidePackageEntity> availabilityGeneratedGuidePackageEntities = new ArrayList<>();
    @NotNull
    @Column(name = "total_option_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal totalOptionPricePerPerson;
    @NotNull
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;

    @Column(name = "is_active")
    private Boolean isActive;

}
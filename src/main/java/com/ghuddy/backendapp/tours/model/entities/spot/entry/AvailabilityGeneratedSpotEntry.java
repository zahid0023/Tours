package com.ghuddy.backendapp.tours.model.entities.spot.entry;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.TourPackageAvailabilityEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_spot_entries")
public class AvailabilityGeneratedSpotEntry extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_availability_id")
    private TourPackageAvailabilityEntity tourPackageAvailabilityEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spot_entry_id")
    private SpotEntryEntity spotEntryEntity;
    @NotNull
    @Column(name = "spot_entry_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal spotEntryPricePerPerson;
    @NotNull
    @Column(name = "is_default", nullable = false)
    private Boolean isDefault = false;
}
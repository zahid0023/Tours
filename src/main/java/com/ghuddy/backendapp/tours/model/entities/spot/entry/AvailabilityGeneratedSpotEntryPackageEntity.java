package com.ghuddy.backendapp.tours.model.entities.spot.entry;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_spot_entry_packages")
public class AvailabilityGeneratedSpotEntryPackageEntity extends BaseEntity {

    @NotNull
    @Column(name = "spot_entry_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal spotEntryPricePerPerson;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_spot_entry_option_id")
    private AvailabilityGeneratedSpotEntryOptionEntity availabilityGeneratedSpotEntryOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "spot_entry_package_id")
    private SpotEntryPackageEntity spotEntryPackageEntity;

}
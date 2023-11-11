package com.ghuddy.backendapp.tours.model.entities.accommodation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_accommodation_packages")
public class AvailabilityGeneratedAccommodationPackageEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_package_id")
    private AccommodationPackageEntity accommodationPackageEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_accommodation_option_id")
    private AvailabilityGeneratedAccommodationOptionEntity availabilityGeneratedAccommodationOptionEntity;
    @NotNull
    @Column(name = "per_night_room_price", nullable = false, precision = 10, scale = 2)
    private BigDecimal accommodationPackagePrice;

}
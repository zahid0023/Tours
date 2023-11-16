package com.ghuddy.backendapp.tours.model.entities.transportation;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.tourpackage.AvailabilityGeneratedTourPackageEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

@Getter
@Setter
@Entity
@Table(name = "availability_generated_transportation_packages")
public class AvailabilityGeneratedTransportationPackageEntity extends BaseEntity {
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "availability_generated_tour_package_id")
    private AvailabilityGeneratedTourPackageEntity availabilityGeneratedTourPackageEntity;
    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_package_id")
    private TransportationPackageEntity transportationPackageEntity;
    @NotNull
    @Column(name = "transportation_package_price_per_person", nullable = false, precision = 10, scale = 2)
    private BigDecimal transportationPackagePrice;

    @Column(name = "is_active")
    private Boolean isActive;

}
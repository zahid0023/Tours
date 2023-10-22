package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.enums.TripType;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "transfer_packages")
@Getter
@Setter
@NoArgsConstructor
public class TransferPackageEntity extends BaseEntity {
    @Column(name = "active")
    private Boolean active;
    @Column(name = "is_ac")
    private Boolean isAc;
    @Column(name = "suitable_for_persons")
    private Integer suitableForPersons;
    @Column(name = "per_vehicle_per_trip_price")
    private BigDecimal unitPrice; // for now let us consider per vehicle per trip price

    @Column(name = "transfer_route")
    private String transferRoute;

    @Enumerated(EnumType.STRING)
    @Column(name = "trip_type")
    private TripType tripType;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_provider_id")
    private TransportationProviderEntity transportationProviderEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_mode_id")
    private TransportationModeEntity transportationModeEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_transfer_option_id")
    private TransferOptionEntity transferOptionEntity;

}

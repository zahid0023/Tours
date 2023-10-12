package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "tour_package_transfer_package")
@Getter
@Setter
@NoArgsConstructor
public class TransferPackageEntity extends BaseEntity {
    @Column(name = "active")
    private Boolean active;
    @Column(name = "is_ac")
    private Boolean isAc;
    @Column(name = "maximum_number_of_travellers")
    private Integer maximumNumberOfTravellers;
    @Column(name = "per_day_price")
    private BigDecimal perDayPrice;
    @Column(name = "number_of_vehicles")
    private Integer numberOfVehicles;
    @Column(name = "total_transfer_package_price")
    private BigDecimal totalTransferPackagePrice;
    @Column(name = "is_included")
    private Boolean isIncluded;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_provider_id")
    private TransportationProviderEntity transportationProviderEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_mode_id")
    private TransportationModeEntity transportationModeEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;

}

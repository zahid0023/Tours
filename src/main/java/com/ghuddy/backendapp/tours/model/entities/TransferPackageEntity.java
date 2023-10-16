package com.ghuddy.backendapp.tours.model.entities;

import com.ghuddy.backendapp.model.db.BaseEntity;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

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
    @Column(name = "maximum_number_of_travellers")
    private Integer maximumNumberOfTravellers;
    @Column(name = "per_day_price")
    private BigDecimal perDayPrice;
    @Column(name = "day_numbers", columnDefinition = "integer[]")
    @Type(type = "com.ghuddy.backendapp.tours.utils.CustomIntegerArrayType")  // Adjust the package and class name accordingly
    private Integer[] dayNumbers;

    @Column(name = "number_of_vehicles")
    private Integer numberOfVehicles;
    @Column(name = "per_person_transfer_package_price")
    private BigDecimal perPersonTransferPackagePrice;

    @Column(name = "transfer_route")
    private String transferRoute;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_provider_id")
    private TransportationProviderEntity transportationProviderEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transportation_mode_id")
    private TransportationModeEntity transportationModeEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_transfer_option_id")
    private TransferOptionEntity transferOptionEntity;

}

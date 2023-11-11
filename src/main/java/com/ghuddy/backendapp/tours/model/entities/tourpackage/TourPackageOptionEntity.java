package com.ghuddy.backendapp.tours.model.entities.tourpackage;

import com.ghuddy.backendapp.model.db.BaseEntity;
import com.ghuddy.backendapp.tours.model.entities.TourPackageOptionCapacityPriceEntity;
import com.ghuddy.backendapp.tours.model.entities.transfer.TransferOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.transportation.TransportationPackageEntity;
import com.ghuddy.backendapp.tours.model.entities.accommodation.AccommodationOptionEntity;
import com.ghuddy.backendapp.tours.model.entities.food.FoodOptionEntity;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Getter
@Setter
@Entity
@Table(name = "tour_package_option")
public class TourPackageOptionEntity extends BaseEntity {

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "tour_package_id")
    private TourPackageEntity tourPackageEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "accommodation_option_id")
    private AccommodationOptionEntity accommodationOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "food_option_id")
    private FoodOptionEntity foodOptionEntity;

    @ManyToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "transfer_option_id")
    private TransferOptionEntity transferOptionEntity;

    @Transient
    private TransportationPackageEntity transportationPackageEntity;

    @OneToOne(mappedBy = "tourPackageOptionEntity", cascade = CascadeType.ALL)
    private TourPackageOptionCapacityPriceEntity tourPackageOptionCapacityPriceEntity;

    @Column(name = "capacity_price_generated")
    private boolean capacityPriceGenerated;

}
